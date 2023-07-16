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
import org.springframework.web.bind.annotation.RestController;

import com.nostratech.movie.dto.ActorCreateRequestDTO;
import com.nostratech.movie.dto.ActorResponseDTO;
import com.nostratech.movie.dto.ActorUpdateRequestDTO;
import com.nostratech.movie.service.ActorService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class ActorResource {
	
	private final ActorService ActorService;
	
	//actor detail
	@GetMapping("/actor/{id}/detail")
	public ResponseEntity<ActorResponseDTO> findActorById(@PathVariable Long id){
		return ResponseEntity.ok().body(ActorService.findActorById(id));
	}
	
	@PostMapping("/actor")
	public ResponseEntity<Void> createNewActor(@RequestBody @Valid List<ActorCreateRequestDTO> dto){
		ActorService.createNewActor(dto);
		return ResponseEntity.created(URI.create("/actor")).build();
	}
	
	
	@PutMapping("/actor/{ActorId}")
	public ResponseEntity<Void> updateActor(@PathVariable Long ActorId, 
		 @RequestBody ActorUpdateRequestDTO dto){
		ActorService.updateActor(ActorId, dto);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/actor/{ActorId}")
	public ResponseEntity<Void> deleteActor(@PathVariable Long ActorId){
		ActorService.deleteActor(ActorId);
		return ResponseEntity.ok().build();
	}
	

}
