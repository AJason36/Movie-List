package com.nostratech.movie.dto;


import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class UsersCreateRequestDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4354298740902018782L;
	@NotBlank
	private String username;
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	
}
