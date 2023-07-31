package com.nostratech.movie.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class ReviewUpdateRequestDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5821849033709470550L;

	private String comment;
	
	private Integer star;
}
