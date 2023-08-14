package com.nostratech.movie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nostratech.movie.domain.Person;
import com.nostratech.movie.dto.ActorQueryDTO;
import com.nostratech.movie.dto.DirectorQueryDTO;

public interface PersonRepository extends JpaRepository<Person, Long> {

	// method name convention
	// find+keyword
	// sql -> select * from Person a where a.id= :
	public Optional<Person> findById(Long id);

	// where id = :id AND deleted=false
	public Optional<Person> findByIdAndDeletedFalse(Long id);

	// sql -> select a from Person a where a.Person_name = :PersonName
	public Optional<Person> findByName(String personName);

	public List<Person> findBySecureIdIn(List<String> personIdList);

	public Optional<Person> findBySecureId(String id);

	public Page<Person> findByNameLikeIgnoreCase(String personName, Pageable pageable);

	@Query("SELECT new com.nostratech.movie.dto.dto.ActorQueryDTO(m.id, ma.name) "
			+ "FROM Movie m "
			+ "JOIN m.actors ma "
			+ "WHERE m.id IN :movieIdList")
	public List<ActorQueryDTO> findActorsByMovieIdList(List<Long> movieIdList);
	 
	@Query("SELECT new com.nostratech.movie.dto.dto.DirectorQueryDTO(m.id, md.name) "
			+ "FROM Movie m "
			+ "JOIN m.directors md "
			+ "WHERE m.id IN :movieIdList")
	public List<DirectorQueryDTO> findDirectorsByMovieIdList(List<Long> movieIdList); 
}
