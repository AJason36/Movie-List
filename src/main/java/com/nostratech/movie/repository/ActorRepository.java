package com.nostratech.movie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nostratech.movie.domain.Actor;


public interface ActorRepository extends JpaRepository<Actor, Long>{

	//method name convention
	//find+keyword
	//sql -> select * from actor a where a.id= :
	public Optional<Actor> findById(Long id);
		
	//where id = :id AND deleted=false
	public Optional<Actor> findByIdAndDeletedFalse(Long id);

	
	//sql -> select a from Actor a where a.Actor_name = :ActorName
	public List<Actor> findByNameLike(String actorName);
	
	
}
