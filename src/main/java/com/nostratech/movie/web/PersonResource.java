package com.nostratech.movie.web;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nostratech.movie.dto.PersonCreateRequestDTO;
import com.nostratech.movie.dto.PersonResponseDTO;
import com.nostratech.movie.dto.PersonUpdateRequestDTO;
import com.nostratech.movie.dto.ResultPageResponseDTO;
import com.nostratech.movie.service.PersonService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class PersonResource {
	
	private final PersonService personService;
		
	@PostMapping("/v1/person")
	public ResponseEntity<Void> createAndUpdateperson(@RequestBody PersonCreateRequestDTO dto){
		personService.createAndUpdatePerson(dto);
		return ResponseEntity.created(URI.create("/v1/person")).build();	
	}
	
	@GetMapping("/v1/person")
	public ResponseEntity<ResultPageResponseDTO<PersonResponseDTO>> findpersonList(
			@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages, 
			@RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
			@RequestParam(name="sortBy", required = true, defaultValue = "name") String sortBy,
			@RequestParam(name="direction", required = true, defaultValue = "asc") String direction,
			@RequestParam(name = "name", required = false) String personName) {
		return ResponseEntity.ok().body(personService.findPersonList(pages, limit, sortBy, direction, personName));
	}
	
	@DeleteMapping("/v1/person/{personId}")
	public ResponseEntity<Void> deleteperson(@PathVariable("personId") String personId){
		personService.deletePerson(personId);
		return ResponseEntity.ok().build();
	}

}
