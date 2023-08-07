package com.nostratech.movie.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nostratech.movie.dto.HelloMessageResponseDTO;
import com.nostratech.movie.service.GreetingService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@SecurityRequirement(name="bearerAuth")
public class HelloResources {
	
	Logger log = LoggerFactory.getLogger(HelloResources.class);

	private GreetingService greetingService;
	
	public HelloResources(GreetingService greetingService) {
		super();
		this.greetingService = greetingService;
	}

	//POST, PUT, DELETE, OPTION, TRACE, HEAD, PATCH
	@GetMapping("/hello")
	public ResponseEntity<HelloMessageResponseDTO> helloWorld() {
		log.trace("this is log TRACE");
		log.debug("this is log DEBUG");
		log.info("this is log INFO");
		log.warn("this is log WARN");
		log.error("this is log ERROR");
		HelloMessageResponseDTO dto = new HelloMessageResponseDTO();
		dto.setMessage(greetingService.sayGreeting());
		return ResponseEntity.accepted().body(dto);
	}
	
	
}
