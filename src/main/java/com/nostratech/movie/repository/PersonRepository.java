package com.nostratech.movie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;import org.springframework.data.jpa.repository.JpaRepository;

import com.nostratech.movie.domain.Person;

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

}
