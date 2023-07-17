package com.nostratech.movie.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UsersResponseDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8511263785007916348L;
	@NotBlank
	private String username;
	@NotBlank
	private String email;
	@NotBlank
	private String password;

}
