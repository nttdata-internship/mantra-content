package com.nttdata.myapp.web.rest.vm;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/content")
public class FileController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

	@RequestMapping("/helloworld")
	public Person helloworld() {
		Person p = new Person();
		p.setBirthDate(new Date());
		p.setLastName("lName");
		p.setFirstName("fname");
		return p;
	}

	@RequestMapping(value = "/persons", method = RequestMethod.POST, produces = { "application/xml",
			"application/json" })
	public Person getAllPersons(
	// (@PathVariable(required=false) String firstName, @RequestParam String
	// lastName,
	// @RequestParam Date birthDate
	) {

		LOGGER.info("Entering helloworld entrypoint with variables {}");

		Person p = new Person();
		p.setBirthDate(Calendar.getInstance().getTime());
		p.setFirstName("Stefan");
		p.setLastName("Neacsu");
		return p;

	}

	@RequestMapping(value = "/helloworld/person.csv")
	public String personAsCSV(HttpServletResponse response) throws IOException {
		response.setContentType("text/plain; charset=utf-8");
		return "";
	}

	// Acces via console curl -X POST \
	// http://localhost:8080/content/upload \
	// -H 'cache-control: no-cache' \
	// -H 'content-type: multipart/form-data;
	// boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
	// -H 'postman-token: b718f3cb-4124-8c5f-138b-d6bfb8bc2917' \
	// -F 'file=@C:\Users\stefan.neacsu\eclipse\java-oxygen\eclipse\eclipse.ini'
	//

	// Acces via browser: ???
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String submit(@RequestParam("file") MultipartFile file) {
		return file.getOriginalFilename();
	}

	@RequestMapping(value = "/download/{file_name}", method = RequestMethod.POST)
	public ResponseEntity<?> getFile(@RequestParam("file") MultipartFile file,
			@PathVariable("file_name") String fileName, HttpServletResponse response) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ZipOutputStream zos = new ZipOutputStream(baos);
			zos.putNextEntry(new ZipEntry(file.getName()));
			// zos.write(("Created " + Calendar.getInstance().getTime()).getBytes());
			zos.write(file.getBytes());

			zos.closeEntry();
			zos.flush();

			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			response.setHeader("Content-Disposition", "attachment; filename=zipArchive.zip");

			return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);

		} catch (IOException ex) {
			LOGGER.error(" An error occured when creating a zipFile", ex);
			throw new RuntimeException("ERROR.", ex);
		}

	}

	// isBornToday, isOlderThan18 (Single rest endpoint )
	// 2. Upload json/xml to server (Name then endpoint /upload)
	// 3. Show all contents of all uploaded files (Name the endpoint /download)
	// 4. Download all file in a zip....as in evry standard download.

}
