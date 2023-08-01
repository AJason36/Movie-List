package com.nostratech.movie.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class MovieDetailDTO implements Serializable {
	
	/**
	 * 
	 */
	private String movieId;
	private String title;
	
	private List<PersonResponseDTO> actors;
	private List<PersonResponseDTO> directors;
	private List<GenreResponseDTO> genres;
}
