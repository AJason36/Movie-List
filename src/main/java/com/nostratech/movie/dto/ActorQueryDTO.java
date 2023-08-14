package com.nostratech.movie.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ActorQueryDTO implements Serializable{
    private Long movieId;
	
	private String actorName;
}
