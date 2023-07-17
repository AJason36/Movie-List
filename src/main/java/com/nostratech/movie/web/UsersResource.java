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

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class UsersResource {
	
	private final UsersService UsersService;
		
	// //Users detail
	// @GetMapping("/v1/Users/{id}/detail")
	// public ResponseEntity<UsersResponseDTO> findUsersById(@PathVariable String id){
	// 	return ResponseEntity.ok().body(UsersService.findUsersById(id));
	// }
	
	// @PostMapping("/v1/Users")
	// public ResponseEntity<Void> createNewUsers(@RequestBody @Valid List<UsersCreateRequestDTO> dto){
	// 	UsersService.createNewUsers(dto);
	// 	return ResponseEntity.created(URI.create("/Users")).build();
	// }
	
	
	// @PutMapping("/v1/Users/{UsersId}")
	// public ResponseEntity<Void> updateUsers(@PathVariable String UsersId, 
	// 	 @RequestBody UsersUpdateRequestDTO dto){
	// 	UsersService.updateUsers(UsersId, dto);
	// 	return ResponseEntity.ok().build();
	// }
	
	// @DeleteMapping("/v1/Users/{UsersId}")
	// public ResponseEntity<Void> deleteUsers(@PathVariable String UsersId){
	// 	UsersService.deleteUsers(UsersId);
	// 	return ResponseEntity.ok().build();
	// }
	@PostMapping("/v1/user")
	public ResponseEntity<Void> createAndUpdateUsers(@RequestBody UsersCreateRequestDTO dto){
		UsersService.createAndUpdateUsers(dto);
		return ResponseEntity.created(URI.create("/v1/user")).build();
		
	}
	
	@GetMapping("/v1/user")
	public ResponseEntity<ResultPageResponseDTO<UsersResponseDTO>> findUsersList(
			@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages, 
			@RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
			@RequestParam(name="sortBy", required = true, defaultValue = "username") String sortBy,
			@RequestParam(name="direction", required = true, defaultValue = "asc") String direction,
			@RequestParam(name="username", required = false) String username){
		return ResponseEntity.ok().body(UsersService.findUsersList(pages, limit, sortBy, direction, username));
	}

}
