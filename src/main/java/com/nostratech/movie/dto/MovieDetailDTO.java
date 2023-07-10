package com.nostratech.movie.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class MovieDetailDTO implements Serializable {
	
	/**
	 * 
	 */

	private Long movieId;
	
	private String movieTitle;
	
	private String genre;
	private String cinema;
	private String actor;
	
	private String review;


}
