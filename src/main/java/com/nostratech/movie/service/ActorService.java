package com.nostratech.movie.service;

import java.util.List;

import com.nostratech.movie.dto.ActorCreateRequestDTO;
import com.nostratech.movie.dto.ActorResponseDTO;
import com.nostratech.movie.dto.ActorUpdateRequestDTO;


public interface ActorService {
	
	public ActorResponseDTO findActorById(Long id);
	
	public void createNewActor(List<ActorCreateRequestDTO> dto);
	
	public void updateActor(Long actorId, ActorUpdateRequestDTO dto);
	
	public void deleteActor(Long actorId);

}
