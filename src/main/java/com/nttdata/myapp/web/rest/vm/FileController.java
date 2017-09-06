package com.nttdata.myapp.web.rest.vm;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
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
			"application/json"})
	public Person getAllPersons(
	// (@PathVariable(required=false) String firstName, @RequestParam String lastName,
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

	
	//Acces via console curl -X POST \
//	  http://localhost:8080/content/upload \
//		  -H 'cache-control: no-cache' \
//		  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
//		  -H 'postman-token: b718f3cb-4124-8c5f-138b-d6bfb8bc2917' \
//		  -F 'file=@C:\Users\stefan.neacsu\eclipse\java-oxygen\eclipse\eclipse.ini'
//		  
		  
   // Acces via browser: ???
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String submit(@RequestParam("file") MultipartFile file) {
		return file.getOriginalFilename();
	}

	// 1. Return Person json/xml/csv with following fields: fName, lName, birthDate,
	// isBornToday, isOlderThan18 (Single rest endpoint )
	// 2. Upload json/xml to server (Name then endpoint /upload)
	// 3. Show all contents of all uploaded files (Name the endpoint /download)
	// 4. Download all file in a zip....as in evry standard download.

}
