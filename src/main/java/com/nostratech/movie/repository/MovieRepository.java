package com.nostratech.movie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nostratech.movie.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	public Optional<Movie> findById(Long id);

	public Optional<Movie> findBySecureId(String id);

	// public List<Movie> findAll();

	// public void save(Movie movie);

	// public void update(Movie movie);

	// where id = :id AND deleted=false
	public Optional<Movie> findByIdAndDeletedFalse(Long id);

	public Optional<Movie> findByTitle(String movieTitle);

	public List<Movie> findBySecureIdIn(List<String> movieIdList);

	public Page<Movie> findByTitleLikeIgnoreCase(String movieTitle, Pageable pageable);

	@Query("SELECT DISTINCT m FROM Movie m "
			+ "INNER JOIN Genres g "
			+ "WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :movieTitle, '%')) "
			+ "AND LOWER(g.genre) LIKE LOWER(CONCAT('%', :genre, '%'))")
	public Page<Movie> findMovieList(String movieTitle, String genre, Pageable pageable);
}
