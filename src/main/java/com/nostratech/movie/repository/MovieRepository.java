package com.nostratech.movie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nostratech.movie.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{
	public Optional<Movie> findById(Long id);
	public Optional<Movie> findBySecureId(String id);
	
	// public List<Movie> findAll();
	
	// public void save(Movie movie);
	
	// public void update(Movie movie);
	
	// public void delete(Long movieId);


}
