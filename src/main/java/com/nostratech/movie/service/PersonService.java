package com.nostratech.movie.service;

import java.util.List;

import com.nostratech.movie.domain.Person;
import com.nostratech.movie.dto.PersonCreateRequestDTO;
import com.nostratech.movie.dto.PersonResponseDTO;
import com.nostratech.movie.dto.PersonUpdateRequestDTO;
import com.nostratech.movie.dto.ResultPageResponseDTO;


public interface PersonService {
	
	public PersonResponseDTO findPersonById(String id);
	
	public void createNewPerson(List<PersonCreateRequestDTO> dto);
	
	public void updatePerson(String personId, PersonUpdateRequestDTO dto);
	
	public void deletePerson(String personId);

	public List<Person> findPersons(List<String> personIdList);
	
	public List<PersonResponseDTO> constructDTO(List<Person> persons);

	public void createAndUpdatePerson(PersonCreateRequestDTO dto);
	
	public ResultPageResponseDTO<PersonResponseDTO> findPersonList(Integer pages, 
			Integer limit, String sortBy, String direction, String personName);
	
}
