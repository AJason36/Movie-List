package com.nostratech.movie.service.impl;

import java.time.LocalDate;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nostratech.movie.domain.Person;
import com.nostratech.movie.dto.PersonCreateRequestDTO;
import com.nostratech.movie.dto.PersonResponseDTO;
import com.nostratech.movie.dto.PersonUpdateRequestDTO;
import com.nostratech.movie.dto.ResultPageResponseDTO;
import com.nostratech.movie.exception.BadRequestException;
import com.nostratech.movie.repository.MovieRepository;
import com.nostratech.movie.repository.PersonRepository;
import com.nostratech.movie.service.PersonService;
import com.nostratech.movie.util.PaginationUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

	private final PersonRepository personRepository;

	@Override
	public PersonResponseDTO findPersonById(String id) {
		// 1. fetch data from databse
		Person Person = personRepository.findBySecureId(id)
				.orElseThrow(() -> new BadRequestException("invalid.PersonId"));
		// 2. Person -> PersonResponseDTO
		PersonResponseDTO dto = new PersonResponseDTO();
		dto.setName(Person.getName());
		dto.setAge(Person.getAge());
		return dto;
	}

	@Override
	public void createNewPerson(List<PersonCreateRequestDTO> dtos) {

		List<Person> Persons = dtos.stream().map((dto) -> {
			Person Person = new Person();
			Person.setName(dto.getName());
			Person.setAge(dto.getAge());
			return Person;
		}).collect(Collectors.toList());

		personRepository.saveAll(Persons);
	}

	@Override
	public void updatePerson(String PersonId, PersonUpdateRequestDTO dto) {
		Person Person = personRepository.findBySecureId(PersonId)
				.orElseThrow(() -> new BadRequestException("invalid.PersonId"));
		Person.setName(dto.getName() == null ? Person.getName() : dto.getName());
		Person.setAge(
				dto.getAge() == null ? Person.getAge() : dto.getAge());

		personRepository.save(Person);
	}

	// oracle db -> flashback technologies
	// softdelete
	@Override
	public void deletePerson(String PersonId) {
		Person person = personRepository.findBySecureId(PersonId)
				.orElseThrow(() -> new BadRequestException("invalid.PersonId"));

		// Check if the person is associated with any movie as actor or director
		if (!person.getMoviesActed().isEmpty() || !person.getMoviesDirected().isEmpty()) {
			throw new BadRequestException("Cannot delete the person, still in movie");
		}
	
		personRepository.delete(person);
	}

	@Override
	public List<Person> findPersons(List<String> personIdList) {
		List<Person> persons = personRepository.findBySecureIdIn(personIdList);
		if (persons.isEmpty())
			throw new BadRequestException("Person cant empty");
		return persons;
	}

	@Override
	public List<PersonResponseDTO> constructDTO(List<Person> persons) {
		return persons.stream().map((p)->{
			PersonResponseDTO dto = new PersonResponseDTO();
			dto.setName(p.getName());
			dto.setAge(p.getAge());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void createAndUpdatePerson(PersonCreateRequestDTO dto) {
		Person person =  personRepository.findByName(dto.getName()).orElse(new Person());
		 if(person.getName()==null) {
			 person.setName(dto.getName().toLowerCase()); //new 
		 }
		 person.setName(dto.getName());
		 person.setAge(dto.getAge());
		 
		 personRepository.save(person);
	}

	@Override
	public ResultPageResponseDTO<PersonResponseDTO> findPersonList(Integer pages, Integer limit, String sortBy,
			String direction, String personName) {
		personName =  StringUtils.isEmpty(personName) ? "%":personName+"%";
		Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
		Pageable pageable = PageRequest.of(pages, limit, sort);
		Page<Person> pageResult =  personRepository.findByNameLikeIgnoreCase(personName, pageable);
		List<PersonResponseDTO> dtos =  pageResult.stream().map((c)->{
			PersonResponseDTO dto = new PersonResponseDTO();
			dto.setName(c.getName());
			dto.setAge(c.getAge());
			return dto;
		}).collect(Collectors.toList());
		return PaginationUtil.createResultPageDTO(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
	}
}
