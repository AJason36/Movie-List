package com.nostratech.movie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nostratech.movie.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
    public Optional<Review> findById(Long id);

	// where id = :id AND deleted=false
	public Optional<Review> findByIdAndDeletedFalse(Long id);

	public Optional<Review> findByStar(Integer star);

	public List<Review> findBySecureIdIn(List<String> reviewIdList);

	public Optional<Review> findBySecureId(String id);

	public Page<Review> findByCommentLikeIgnoreCase(String comment, Pageable pageable);
}
