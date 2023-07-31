package com.nostratech.movie.service;

import java.util.List;

import com.nostratech.movie.domain.Review;
import com.nostratech.movie.dto.ReviewCreateRequestDTO;
import com.nostratech.movie.dto.ReviewResponseDTO;
import com.nostratech.movie.dto.ReviewUpdateRequestDTO;
import com.nostratech.movie.dto.ResultPageResponseDTO;

public interface ReviewService {
    public ReviewResponseDTO findReviewById(String id);
	
	public void createReviews(List<ReviewCreateRequestDTO> dto);

	public void createOneReview(ReviewCreateRequestDTO dto);
	
	public void updateReview(String reviewId, ReviewUpdateRequestDTO dto);
	
	public void createAndUpdateReview(ReviewCreateRequestDTO dto);
	public void deleteReview(String reviewId);

	public List<Review> findReviews(List<String> reviewIdList);
	
	public List<ReviewResponseDTO> constructDTO(List<Review> reviews);

	public ResultPageResponseDTO<ReviewResponseDTO> findReviewList(Integer pages, 
			Integer limit, String sortBy, String direction, String comment);
	
}
