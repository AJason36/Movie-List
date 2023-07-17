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

	private final PersonRepository PersonRepository;
	private final MovieRepository MovieRepository;

	@Override
	public PersonResponseDTO findPersonById(String id) {
		// TODO Auto-generated method stub
		// 1. fetch data from databse
		Person Person = PersonRepository.findBySecureId(id)
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

		PersonRepository.saveAll(Persons);
	}

	@Override
	public void updatePerson(String PersonId, PersonUpdateRequestDTO dto) {
		Person Person = PersonRepository.findBySecureId(PersonId)
				.orElseThrow(() -> new BadRequestException("invalid.PersonId"));
		Person.setName(dto.getName() == null ? Person.getName() : dto.getName());
		Person.setAge(
				dto.getAge() == null ? Person.getAge() : dto.getAge());

		PersonRepository.save(Person);
	}

	// oracle db -> flashback technologies
	// softdelete
	@Override
	public void deletePerson(String PersonId) {
		// 1 select data
		// 2 delete
		// or
		// 1 delete (harddelete)
//		PersonRepository.deleteById(PersonId);
		Person Person = PersonRepository.findBySecureId(PersonId)
				.orElseThrow(() -> new BadRequestException("invalid.PersonId"));
		PersonRepository.delete(Person);
		// softdelete
		// 1. select data deleted=false
//		Person Person = PersonRepository.findByIdAndDeletedFalse(PersonId)
//				.orElseThrow(() -> new BadRequestException("invalid.PersonId"));
//
//		// 2. update deleted=true
//		Person.setDeleted(Boolean.TRUE);
//		PersonRepository.save(Person);
	}

	@Override
	public List<Person> findPersons(List<String> PersonIdList) {
		List<Person> Persons = PersonRepository.findBySecureIdIn(PersonIdList);
		if (Persons.isEmpty())
			throw new BadRequestException("Person cant empty");
		return Persons;
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
		Person person =  PersonRepository.findByName(dto.getName()).orElse(new Person());
		 if(person.getName()==null) {
			 person.setName(dto.getName().toLowerCase()); //new 
		 }
		 person.setName(dto.getName());
		 person.setAge(dto.getAge());
		 
		 PersonRepository.save(person);
	}

	@Override
	public ResultPageResponseDTO<PersonResponseDTO> findPersonList(Integer pages, Integer limit, String sortBy,
			String direction, String personName) {
		personName =  StringUtils.isEmpty(personName) ? "%":personName+"%";
		Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
		Pageable pageable = PageRequest.of(pages, limit, sort);
		Page<Person> pageResult =  PersonRepository.findByNameLikeIgnoreCase(personName, pageable);
		List<PersonResponseDTO> dtos =  pageResult.stream().map((c)->{
			PersonResponseDTO dto = new PersonResponseDTO();
			dto.setName(c.getName());
			dto.setAge(c.getAge());
			return dto;
		}).collect(Collectors.toList());
		return PaginationUtil.createResultPageDTO(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
	}
}
