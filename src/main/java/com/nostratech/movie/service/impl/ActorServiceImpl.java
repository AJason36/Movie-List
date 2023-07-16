package com.nostratech.movie.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nostratech.movie.domain.Actor;
import com.nostratech.movie.dto.ActorCreateRequestDTO;
import com.nostratech.movie.dto.ActorResponseDTO;
import com.nostratech.movie.dto.ActorUpdateRequestDTO;
import com.nostratech.movie.exception.BadRequestException;
import com.nostratech.movie.repository.ActorRepository;
import com.nostratech.movie.service.ActorService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ActorServiceImpl implements ActorService {

	private final ActorRepository ActorRepository;

	@Override
	public ActorResponseDTO findActorById(Long id) {
		// TODO Auto-generated method stub
		// 1. fetch data from databse
		Actor Actor = ActorRepository.findById(id).orElseThrow(() -> new BadRequestException("invalid.ActorId"));
		// 2. Actor -> ActorResponseDTO
		ActorResponseDTO dto = new ActorResponseDTO();
		dto.setActorName(Actor.getName());
		dto.setAge(Actor.getAge());
		return dto;
	}

	@Override
	public void createNewActor(List<ActorCreateRequestDTO> dtos) {

		List<Actor> Actors = dtos.stream().map((dto) -> {
			Actor Actor = new Actor();
			Actor.setName(dto.getActorName());
			Actor.setAge(Actor.getAge());
			return Actor;
		}).collect(Collectors.toList());

		ActorRepository.saveAll(Actors);
	}

	@Override
	public void updateActor(Long ActorId, ActorUpdateRequestDTO dto) {
		Actor Actor = ActorRepository.findById(ActorId)
				.orElseThrow(() -> new BadRequestException("invalid.ActorId"));
		Actor.setName(dto.getActorName() == null ? Actor.getName() : dto.getActorName());
		Actor.setAge(
				dto.getAge() == null ? Actor.getAge() : dto.getAge());

		ActorRepository.save(Actor);

	}

	// oracle db -> flashback technologies
	// softdelete
	@Override
	public void deleteActor(Long ActorId) {
		// 1 select data
		// 2 delete
		// or
		// 1 delete (harddelete)
		ActorRepository.deleteById(ActorId);

		// softdelete
		// 1. select data deleted=false
//		Actor Actor = ActorRepository.findByIdAndDeletedFalse(ActorId)
//				.orElseThrow(() -> new BadRequestException("invalid.ActorId"));
//
//		// 2. update deleted=true
//		Actor.setDeleted(Boolean.TRUE);
//		ActorRepository.save(Actor);
	}

}
