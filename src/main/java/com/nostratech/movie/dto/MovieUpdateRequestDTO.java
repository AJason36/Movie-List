package com.nostratech.movie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class MovieUpdateRequestDTO {
	
	@NotBlank
	private String movieTitle;
	
	@NotBlank
	private String actor;
	
	@NotBlank
	private String cinema;
	
	private String review;

}
