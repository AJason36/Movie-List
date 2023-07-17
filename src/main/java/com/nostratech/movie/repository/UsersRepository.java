package com.nostratech.movie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;import org.springframework.data.jpa.repository.JpaRepository;

import com.nostratech.movie.domain.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

	// method name convention
	// find+keyword
	// sql -> select * from User a where a.id= :
	public Optional<Users> findById(Long id);

	// where id = :id AND deleted=false
	public Optional<Users> findByIdAndDeletedFalse(Long id);

	// sql -> select a from User a where a.User_name = :UserName
	public Optional<Users> findByUsername(String userName);

	public List<Users> findBySecureIdIn(List<String> userIdList);

	public Optional<Users> findBySecureId(String id);

	public Page<Users> findByUsernameLikeIgnoreCase(String userName, Pageable  pageable);

}
