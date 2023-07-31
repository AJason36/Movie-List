package com.nostratech.movie.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MovieCreateDTO {
	
	@NotBlank
	private String title;
	
	@NotBlank
	private List<String> genreIdList;

	@NotEmpty
	private List<String> actorIdList;

	@NotEmpty
	private List<String> directorIdList;

}
