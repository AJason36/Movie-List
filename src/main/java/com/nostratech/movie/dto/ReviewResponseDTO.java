package com.nostratech.movie.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReviewResponseDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1120258070824123381L;

	private String comment;
	
	private Integer star;

	private String username; // tampilin username
}
