package com.nostratech.movie.service;

import java.util.List;

import com.nostratech.movie.dto.MovieCreateDTO;
import com.nostratech.movie.dto.MovieDetailDTO;
import com.nostratech.movie.dto.MovieUpdateRequestDTO;


public interface MovieService {
	
	public MovieDetailDTO findMovieDetailById(Long movieId);
	
	public List<MovieDetailDTO> findMovieListDetail();
	
	public void createNewMovie(MovieCreateDTO dto);
	
	public void updateMovie(Long movieId, MovieUpdateRequestDTO dto);
	
	public void deleteMovie(Long movieId);
	
}