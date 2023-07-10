package com.nostratech.movie.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nostratech.movie.domain.Movie;
import com.nostratech.movie.repository.MovieRepository;

import lombok.Data;

@Data
public class MovieRepositoryImpl implements MovieRepository{

	private Map<Long, Movie> MovieMap;

	
	@Override
	public Movie findMovieById(Long id) {
		Movie movie = MovieMap.get(id);
		return movie;
	}


	@Override
	public List<Movie> findAll() {
		List<Movie> MovieList = new ArrayList<Movie>(MovieMap.values());
		return MovieList;
	}


	@Override
	public void save(Movie Movie) {
		int size = MovieMap.size();
		Movie.setId((long) size+1);
		MovieMap.put(Movie.getId(), Movie);
	}


	@Override
	public void update(Movie Movie) {
		MovieMap.put(Movie.getId(), Movie);	
	}


	@Override
	public void delete(Long MovieId) {
		MovieMap.remove(MovieId);
		
	}

}
