package com.nostratech.movie.dto;


import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class ReviewCreateRequestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1399787788257915734L;

	private String comment;
	
	@NotNull
	private Integer star;

	private String userId;

	private String movieId;
}
