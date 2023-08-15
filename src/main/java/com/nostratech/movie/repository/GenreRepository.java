package com.nostratech.movie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nostratech.movie.domain.Genres;
import com.nostratech.movie.dto.GenreQueryDTO;

public interface GenreRepository extends JpaRepository<Genres, Long> {

	// method Genre convention
	// find+keyword
	// sql -> select * from Genres a where a.id= :
	public Optional<Genres> findById(Long id);

	// where id = :id AND deleted=false
	public Optional<Genres> findByIdAndDeletedFalse(Long id);

	// sql -> select a from Genres a where a.Genres_Genre = :genre
	public Optional<Genres> findByGenre(String genre);
	public List<Genres> findByGenreIgnoreCaseIn(List<String> genres);

	public List<Genres> findBySecureIdIn(List<String> genreIdList);

	public Optional<Genres> findBySecureId(String id);

	public Page<Genres> findByGenreLikeIgnoreCase(String genre, Pageable pageable);

	@Query("SELECT new com.nostratech.movie.dto.GenreQueryDTO(m.id, mg.genre) "
			+ "FROM Movie m "
			+ "JOIN m.genre mg "
			+ "WHERE m.id IN :movieIdList")
	public List<GenreQueryDTO> findGenreByMovieIdList(List<Long> movieIdList);

}
