package com.nostratech.movie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;import org.springframework.data.jpa.repository.JpaRepository;

import com.nostratech.movie.domain.Genres;

public interface GenreRepository extends JpaRepository<Genres, Long> {

	// method Genre convention
	// find+keyword
	// sql -> select * from Genres a where a.id= :
	public Optional<Genres> findById(Long id);

	// where id = :id AND deleted=false
	public Optional<Genres> findByIdAndDeletedFalse(Long id);

	// sql -> select a from Genres a where a.Genres_Genre = :genre
	public Optional<Genres> findByGenre(String genre);

	public List<Genres> findBySecureIdIn(List<String> genreIdList);

	public Optional<Genres> findBySecureId(String id);

	public Page<Genres> findByGenreLikeIgnoreCase(String genre, Pageable pageable);

}
