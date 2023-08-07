package com.nostratech.movie.web;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nostratech.movie.dto.MovieDetailDTO;
import com.nostratech.movie.dto.MovieCreateDTO;
import com.nostratech.movie.dto.MovieUpdateRequestDTO;
import com.nostratech.movie.dto.ResultPageResponseDTO;
import com.nostratech.movie.service.MovieService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@SecurityRequirement(name="bearerAuth")
public class MovieResource {
	
	private final MovieService movieService;

	@GetMapping("/v1/movie/{movieId}")
	public ResponseEntity<MovieDetailDTO> findMovieDetail(@PathVariable("movieId") String id) {
		StopWatch stopWatch = new StopWatch();
		log.info("start findMovieDetail "+id);
		stopWatch.start();
		MovieDetailDTO result =  movieService.findMovieDetailById(id);
		log.info("finish findMovieDetail. execution time = {}",stopWatch.getTotalTimeMillis());
		return ResponseEntity.ok(result);

	}
	
	//nama yang salah /save-Movie /create-Movie
	@PostMapping("/v1/movie")
	public ResponseEntity<Void> createANewMovie(@RequestBody MovieCreateDTO dto){
		movieService.createNewMovie(dto);
		return ResponseEntity.created(URI.create("/v1/movie")).build();
	}
	
	@GetMapping("/v1/movie")
	public ResponseEntity<ResultPageResponseDTO<MovieDetailDTO>> findMovieList(
		@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages, 
			@RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
			@RequestParam(name="sortBy", required = true, defaultValue = "title") String sortBy,
			@RequestParam(name="direction", required = true, defaultValue = "asc") String direction,
			@RequestParam(name = "title", required = false) String title) {
		return ResponseEntity.ok().body(movieService.findMovieList(pages, limit, sortBy, direction, title));
	}
	
	//PUT /movie
	@PutMapping("/v1/movie/{movieId}")
	public ResponseEntity<Void> updateMovie(@PathVariable("movieId") String movieId, 
			@RequestBody MovieUpdateRequestDTO dto){
		movieService.updateMovie(movieId, dto);
		return ResponseEntity.ok().build();
	}
	
	//DELETE /movie
	@DeleteMapping("/v1/movie/{movieId}")
	public ResponseEntity<Void> deleteMovie(@PathVariable("movieId") String movieId){
		movieService.deleteMovie(movieId);
		return ResponseEntity.ok().build();
	}
}
