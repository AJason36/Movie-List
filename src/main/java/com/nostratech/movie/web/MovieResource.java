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
import org.springframework.web.bind.annotation.RestController;

import com.nostratech.movie.dto.MovieDetailDTO;
import com.nostratech.movie.dto.MovieCreateDTO;
import com.nostratech.movie.dto.MovieUpdateRequestDTO;
import com.nostratech.movie.service.MovieService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
public class MovieResource {
	
	private final MovieService MovieService;

	//nama yang salah /get-Movie/{MovieId}
	@GetMapping("/movie/{MovieId}")
	public MovieDetailDTO findMovieDetail(@PathVariable("MovieId") Long id) {
		StopWatch stopWatch = new StopWatch();
		log.info("start findMovieDetail "+id);
		stopWatch.start();
		MovieDetailDTO result =  MovieService.findMovieDetailById(id);
		log.info("finish findMovieDetail. execution time = {}",stopWatch.getTotalTimeMillis());
		return result;

	}
	
	//nama yang salah /save-Movie /create-Movie
	@PostMapping("/movie")
	public ResponseEntity<Void> createANewMovie(@RequestBody MovieCreateDTO dto){
		MovieService.createNewMovie(dto);
		return ResponseEntity.created(URI.create("/  ")).build();
	}
	
	@GetMapping("/movie")
	public ResponseEntity<List<MovieDetailDTO>> findMovieList(){
		return ResponseEntity.ok().body(MovieService.findMovieListDetail());
		
	}
	
	//PUT /movie
	@PutMapping("/movie/{MovieId}")
	public ResponseEntity<Void> updateMovie(@PathVariable("MovieId") Long MovieId, 
			@RequestBody MovieUpdateRequestDTO dto){
		MovieService.updateMovie(MovieId, dto);
		return ResponseEntity.ok().build();
	}
	
	//DELETE /movie
	@DeleteMapping("/movie/{MovieId}")
	public ResponseEntity<Void> deleteMovie(@PathVariable("MovieId") Long MovieId){
		MovieService.deleteMovie(MovieId);
		return ResponseEntity.ok().build();
	}
	
}
