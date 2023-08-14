package com.nostratech.movie.dto;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewQueryDTO implements Serializable{
    private String comment;
	
	private Integer star;

	private String username;
}
