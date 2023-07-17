package com.nostratech.movie.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class UsersUpdateRequestDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3995476856482292288L;
	@NotBlank
	private String username;
	@NotBlank
	private String email;
	@NotBlank
	private String password;
}
