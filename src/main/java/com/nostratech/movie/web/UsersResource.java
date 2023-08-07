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

import com.nostratech.movie.dto.UsersCreateRequestDTO;
import com.nostratech.movie.dto.UsersResponseDTO;
import com.nostratech.movie.dto.UsersUpdateRequestDTO;
import com.nostratech.movie.dto.ResultPageResponseDTO;
import com.nostratech.movie.service.UsersService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@SecurityRequirement(name="bearerAuth")
public class UsersResource {
	
	private final UsersService usersService;

	@PostMapping("/v1/user")
	public ResponseEntity<Void> createUsers(@RequestBody UsersCreateRequestDTO dto) {
		usersService.createOneUsers(dto);
		return ResponseEntity.created(URI.create("/v1/user")).build();
	}
	
	@PutMapping("/v1/user/{userId}")
	public ResponseEntity<Void> updateUser(@PathVariable("userId") String userId, 
			@RequestBody UsersUpdateRequestDTO dto) {
		usersService.updateUsers(userId, dto);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/v1/user")
	public ResponseEntity<ResultPageResponseDTO<UsersResponseDTO>> findUsersList(
			@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages, 
			@RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
			@RequestParam(name="sortBy", required = true, defaultValue = "username") String sortBy,
			@RequestParam(name="direction", required = true, defaultValue = "asc") String direction,
			@RequestParam(name = "username", required = false) String username) {
		return ResponseEntity.ok().body(usersService.findUsersList(pages, limit, sortBy, direction, username));
	}

	@DeleteMapping("/v1/user/{userId}")
	public ResponseEntity<Void> deleteuser(@PathVariable("userId") String userId){
		usersService.deleteUsers(userId);
		return ResponseEntity.ok().build();
	}

}
