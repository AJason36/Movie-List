package com.nostratech.movie.service;

import java.util.List;

import com.nostratech.movie.domain.Movie;
import com.nostratech.movie.dto.MovieCreateDTO;
import com.nostratech.movie.dto.MovieDetailDTO;
import com.nostratech.movie.dto.MovieUpdateRequestDTO;
import com.nostratech.movie.dto.ResultPageResponseDTO;


public interface MovieService {
	
	public MovieDetailDTO findMovieDetailById(String movieId);

	public Movie findMovie(String movieId);
	
	public List<MovieDetailDTO> findMovieListDetail();
	
	public void createNewMovie(MovieCreateDTO dto);
	
	public void updateMovie(String movieId, MovieUpdateRequestDTO dto);
	
	public void deleteMovie(String movieId);

	public ResultPageResponseDTO<MovieDetailDTO> findMovieList(Integer pages, 
			Integer limit, String sortBy, String direction, String movieTitle);
	
	public MovieDetailDTO constructDTO(Movie movie);
	
}
