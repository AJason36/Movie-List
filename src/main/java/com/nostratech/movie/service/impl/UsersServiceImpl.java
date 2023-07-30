package com.nostratech.movie.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nostratech.movie.domain.Users;
import com.nostratech.movie.dto.UsersCreateRequestDTO;
import com.nostratech.movie.dto.UsersResponseDTO;
import com.nostratech.movie.dto.UsersUpdateRequestDTO;
import com.nostratech.movie.dto.ResultPageResponseDTO;
import com.nostratech.movie.exception.BadRequestException;
import com.nostratech.movie.repository.MovieRepository;
import com.nostratech.movie.repository.UsersRepository;
import com.nostratech.movie.service.UsersService;
import com.nostratech.movie.util.PaginationUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

	private final UsersRepository usersRepository;

	@Override
	public UsersResponseDTO findUsersById(String id) {
		// TODO Auto-generated method stub
		// 1. fetch data from databse
		Users users = usersRepository.findBySecureId(id)
				.orElseThrow(() -> new BadRequestException("invalid.usersId"));
		// 2. Users -> UsersResponseDTO
		UsersResponseDTO dto = new UsersResponseDTO();
		dto.setUsername(users.getUsername());
		dto.setEmail(users.getEmail());
		dto.setPassword(users.getPassword());
		return dto;
	}

	@Override
	public void createNewUsers(List<UsersCreateRequestDTO> dtos) {

		List<Users> userss = dtos.stream().map((dto) -> {
			Users users = new Users();
			users.setUsername(dto.getUsername());
			users.setEmail(dto.getEmail());
			users.setPassword(dto.getPassword());
			return users;
		}).collect(Collectors.toList());

		usersRepository.saveAll(userss);
	}

	@Override
	public void createOneUsers(UsersCreateRequestDTO dto) {
		Users users = new Users();
		users.setUsername(dto.getUsername());
		users.setEmail(dto.getEmail());
		users.setPassword(dto.getPassword());
	
		usersRepository.save(users);
	}

	@Override
	public void updateUsers(String usersId, UsersUpdateRequestDTO dto) {
		Users users = usersRepository.findBySecureId(usersId)
				.orElseThrow(() -> new BadRequestException("invalid.usersId"));
		users.setUsername(dto.getUsername() == null ? users.getUsername() : dto.getUsername());
		users.setEmail(
				dto.getEmail() == null ? users.getEmail() : dto.getEmail());
		users.setPassword(
				dto.getPassword() == null ? users.getPassword() : dto.getPassword());
		usersRepository.save(users);
	}

	// oracle db -> flashback technologies
	// softdelete
	@Override
	public void deleteUsers(String usersId) {
		// 1 select data
		// 2 delete
		// or
		// 1 delete (harddelete)
//		usersRepository.deleteById(usersId);
		Users users = usersRepository.findBySecureId(usersId)
				.orElseThrow(() -> new BadRequestException("invalid.usersId"));
		usersRepository.delete(users);
		// softdelete
		// 1. select data deleted=false
//		Users Users = usersRepository.findByIdAndDeletedFalse(usersId)
//				.orElseThrow(() -> new BadRequestException("invalid.usersId"));
//
//		// 2. update deleted=true
//		users.setDeleted(Boolean.TRUE);
//		usersRepository.save(Users);
	}

	@Override
	public List<Users> findUsers(List<String> usersIdList) {
		List<Users> Userss = usersRepository.findBySecureIdIn(usersIdList);
		if (Userss.isEmpty())
			throw new BadRequestException("Users cant empty");
		return Userss;
	}

	@Override
	public List<UsersResponseDTO> constructDTO(List<Users> users) {
		return users.stream().map((u)->{
			UsersResponseDTO dto = new UsersResponseDTO();
			dto.setUsername(u.getUsername());
			dto.setEmail(u.getEmail());
			dto.setPassword(u.getPassword());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void createAndUpdateUsers(UsersCreateRequestDTO dto) {
		Users users =  usersRepository.findByUsername(dto.getUsername()).orElse(new Users());
		if(users.getUsername()==null) {
			 users.setUsername(dto.getUsername().toLowerCase()); //new 
		 }
		users.setUsername(dto.getUsername());
		users.setEmail(dto.getEmail());
		users.setPassword(dto.getPassword());
		 
		usersRepository.save(users);
	}

	@Override
	public ResultPageResponseDTO<UsersResponseDTO> findUsersList(Integer pages, Integer limit, String sortBy,
			String direction, String username) {
		username =  StringUtils.isEmpty(username) ? "%":username+"%";
		Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
		Pageable pageable = PageRequest.of(pages, limit, sort);
		Page<Users> pageResult =  usersRepository.findByUsernameLikeIgnoreCase(username, pageable);
		List<UsersResponseDTO> dtos =  pageResult.stream().map((u)->{
			UsersResponseDTO dto = new UsersResponseDTO();
			dto.setUsername(u.getUsername());
			dto.setEmail(u.getEmail());
			dto.setPassword(u.getPassword());
			return dto;
		}).collect(Collectors.toList());
		return PaginationUtil.createResultPageDTO(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
	}
}
