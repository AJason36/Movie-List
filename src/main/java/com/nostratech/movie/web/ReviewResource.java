package com.nostratech.movie.web;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nostratech.movie.domain.Review;
import com.nostratech.movie.dto.ResultPageResponseDTO;
import com.nostratech.movie.dto.ReviewCreateRequestDTO;
import com.nostratech.movie.dto.ReviewResponseDTO;
import com.nostratech.movie.dto.ReviewUpdateRequestDTO;
import com.nostratech.movie.service.ReviewService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@SecurityRequirement(name="bearerAuth")
public class ReviewResource {
    private final ReviewService reviewService; 
    
    @PostMapping("/v1/review")
    public ResponseEntity<Void> createReview(@RequestBody ReviewCreateRequestDTO dto) {
        reviewService.createOneReview(dto);
        return ResponseEntity.created(URI.create("/v1/review")).build();
    }

    @PutMapping("/v1/review/{reviewId}")
	public ResponseEntity<Void> updateReview(@PathVariable("reviewId") String reviewId, 
            @RequestBody ReviewUpdateRequestDTO dto) {
        reviewService.updateReview(reviewId, dto);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/v1/review")
	public ResponseEntity<ResultPageResponseDTO<ReviewResponseDTO>> findReviewList(
			@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages, 
			@RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
			@RequestParam(name="sortBy", required = true, defaultValue = "star") String sortBy,
			@RequestParam(name="direction", required = true, defaultValue = "asc") String direction,
            @RequestParam(name = "username", required = false) String username) // harusnya sort by star
    {
        return ResponseEntity.ok().body(reviewService.findReviewList(pages, limit, sortBy, direction, username));
    }
    
    @DeleteMapping("/v1/review/{reviewId}")
	public ResponseEntity<Void> deleteReview(@PathVariable("reviewId") String reviewId){
		reviewService.deleteReview(reviewId);
		return ResponseEntity.ok().build();
	}
}
