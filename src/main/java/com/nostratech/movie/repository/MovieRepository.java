package com.nostratech.movie.repository;

import java.util.List;

import com.nostratech.movie.domain.Movie;

public interface MovieRepository {
	public Movie findMovieById(Long id);
	
	public List<Movie> findAll();
	
	public void save(Movie movie);
	
	public void update(Movie movie);
	
	public void delete(Long movieId);


}
