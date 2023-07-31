package com.nostratech.movie.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nostratech.movie.domain.Review;
import com.nostratech.movie.dto.ResultPageResponseDTO;
import com.nostratech.movie.dto.ReviewCreateRequestDTO;
import com.nostratech.movie.dto.ReviewResponseDTO;
import com.nostratech.movie.dto.ReviewUpdateRequestDTO;
import com.nostratech.movie.exception.BadRequestException;
import com.nostratech.movie.repository.MovieRepository;
import com.nostratech.movie.repository.ReviewRepository;
import com.nostratech.movie.repository.UsersRepository;
import com.nostratech.movie.service.MovieService;
import com.nostratech.movie.service.ReviewService;
import com.nostratech.movie.service.UsersService;
import com.nostratech.movie.util.PaginationUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieService movieService;
    private final UsersService usersService;

    public ReviewResponseDTO findReviewById(String id) {
        Review review = reviewRepository.findBySecureId(id)
                .orElseThrow(() -> new BadRequestException("invalid.reviewId"));
                
        ReviewResponseDTO dto = new ReviewResponseDTO();
        dto.setMovie(movieService.constructDTO(review.getMovie()));
        dto.setUsers(usersService.constructDTO(review.getUser()));
        dto.setComment(review.getComment());
        dto.setStar(review.getStar());
        return dto;
    }
	
    public void createReviews(List<ReviewCreateRequestDTO> dtos) {
        List<Review> reviews = dtos.stream().map((dto) -> {
            Review review = new Review();
            review.setComment(dto.getComment());
            review.setStar(dto.getStar());
            return review;
        }).collect(Collectors.toList());
        reviewRepository.saveAll(reviews);
    }
	
    public void createOneReview(ReviewCreateRequestDTO dto) {
        Review review = new Review();
        review.setComment(dto.getComment());
        review.setStar(dto.getStar());
        reviewRepository.save(review);
    }
    public void updateReview(String reviewId, ReviewUpdateRequestDTO dto) {
        Review review = reviewRepository.findBySecureId(reviewId)
                .orElseThrow(() -> new BadRequestException("invalid.reviewId"));
        review.setComment(dto.getComment());
        review.setStar(dto.getStar());
        reviewRepository.save(review);
    }
	
    public void createAndUpdateReview(ReviewCreateRequestDTO dto) {
        // Review review =  reviewRepository.findByStar(dto.getUsername()).orElse(new Review());
        // if (review.getUsername() == null) {
        //     review.setUsername(dto.getUsername().toLowerCase()); //new 
        // }
        // review.setComment(dto.getComment());
        // review.setStar(dto.getStar());
        // reviewRepository.save(review);
    }

    public void deleteReview(String reviewId) {
        Review review = reviewRepository.findBySecureId(reviewId)
                .orElseThrow(() -> new BadRequestException("invalid.reviewId"));
        reviewRepository.delete(review);
    }

    public List<Review> findReviews(List<String> reviewIdList) {
        List<Review> reviews = reviewRepository.findBySecureIdIn(reviewIdList);
		if (reviews.isEmpty())
			throw new BadRequestException("Review cant empty");
		return reviews;
    }
	
    public List<ReviewResponseDTO> constructDTO(List<Review> reviews) {
        return reviews.stream().map((review) -> {
            ReviewResponseDTO dto = new ReviewResponseDTO();
            dto.setComment(review.getComment());
            dto.setStar(review.getStar());
            return dto;
        }).collect(Collectors.toList());
    }

	
	public ResultPageResponseDTO<ReviewResponseDTO> findReviewList(Integer pages, 
            Integer limit, String sortBy, String direction, String comment) {
		comment =  StringUtils.isEmpty(comment) ? "%":comment+"%";
		Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
		Pageable pageable = PageRequest.of(pages, limit, sort);
		Page<Review> pageResult =  reviewRepository.findByCommentLikeIgnoreCase(comment, pageable);
		List<ReviewResponseDTO> dtos =  pageResult.stream().map((r)->{
			ReviewResponseDTO dto = new ReviewResponseDTO();
            dto.setComment(r.getComment());
            dto.setStar(r.getStar());
			return dto;
		}).collect(Collectors.toList());
		return PaginationUtil.createResultPageDTO(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());      
    }
	
    
}
