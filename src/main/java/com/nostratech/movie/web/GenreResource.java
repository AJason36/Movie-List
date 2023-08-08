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

import com.nostratech.movie.dto.GenreCreateRequestDTO;
import com.nostratech.movie.dto.GenreResponseDTO;
import com.nostratech.movie.dto.GenreUpdateRequestDTO;
import com.nostratech.movie.dto.ResultPageResponseDTO;
import com.nostratech.movie.dto.UsersUpdateRequestDTO;
import com.nostratech.movie.service.GenreService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
// @SecurityRequirement(name="bearerAuth")
public class GenreResource {
	
	private final GenreService genreService;
		
	@PostMapping("/v1/genre")
	public ResponseEntity<Void> createGenre(@RequestBody GenreCreateRequestDTO dto){
		genreService.createNewGenre(dto);
		return ResponseEntity.created(URI.create("/v1/genre")).build();	
	}
	
	@GetMapping("/v1/genre")
	public ResponseEntity<ResultPageResponseDTO<GenreResponseDTO>> findGenreList(
			@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages, 
			@RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
			@RequestParam(name="sortBy", required = true, defaultValue = "genre") String sortBy,
			@RequestParam(name="direction", required = true, defaultValue = "asc") String direction,
			@RequestParam(name = "genre", required = false) String genre) {
		return ResponseEntity.ok().body(genreService.findGenreList(pages, limit, sortBy, direction, genre));
	}
	
	@DeleteMapping("/v1/genre/{genreId}")
    public ResponseEntity<Void> deleteGenre(@PathVariable("genreId") String genreId) {
        genreService.deleteGenre(genreId);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/v1/genre/{genreId}")
	public ResponseEntity<Void> updategenre(@PathVariable("genreId") String genreId, 
			@RequestBody GenreUpdateRequestDTO dto) {
		genreService.updateGenre(genreId, dto);
		return ResponseEntity.ok().build();
	}
}
