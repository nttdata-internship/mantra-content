package com.nttdata.myapp.web.rest.vm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class FileController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

	@RequestMapping("/helloworld")
	public String helloworld() {
		return "helloworld";
	}

	@RequestMapping(value = "/{firstName}", method = RequestMethod.POST)
	public String helloworld(@PathVariable String firstName, @RequestParam String lastName) {

		LOGGER.info("Entering helloworld entrypoint with variables {}", firstName, lastName);

		return "Hello " + firstName + "lastName =" + lastName;
	}

	// 1. Return Person json/xml/csv with following fields: fName, lName, birthDate, isBornToday, isOlderThan18 (Single rest endpoint )
	// 2. Upload json/xml to server (Name then endpoint /upload)
	// 3. Show all contents of all uploaded files (Name the endpoint /download)
	// 4. Download all file in a zip....as in evry standard download.

}
