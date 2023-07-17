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

	private final UsersRepository UsersRepository;
	private final MovieRepository MovieRepository;

	@Override
	public UsersResponseDTO findUsersById(String id) {
		// TODO Auto-generated method stub
		// 1. fetch data from databse
		Users Users = UsersRepository.findBySecureId(id)
				.orElseThrow(() -> new BadRequestException("invalid.UsersId"));
		// 2. Users -> UsersResponseDTO
		UsersResponseDTO dto = new UsersResponseDTO();
		dto.setUsername(Users.getUsername());
		dto.setEmail(Users.getEmail());
		dto.setPassword(Users.getPassword());
		return dto;
	}

	@Override
	public void createNewUsers(List<UsersCreateRequestDTO> dtos) {

		List<Users> Userss = dtos.stream().map((dto) -> {
			Users Users = new Users();
			Users.setUsername(dto.getUsername());
			Users.setEmail(dto.getEmail());
			Users.setPassword(dto.getPassword());
			return Users;
		}).collect(Collectors.toList());

		UsersRepository.saveAll(Userss);
	}

	@Override
	public void updateUsers(String UsersId, UsersUpdateRequestDTO dto) {
		Users Users = UsersRepository.findBySecureId(UsersId)
				.orElseThrow(() -> new BadRequestException("invalid.UsersId"));
		Users.setUsername(dto.getUsername() == null ? Users.getUsername() : dto.getUsername());
		Users.setEmail(
				dto.getEmail() == null ? Users.getEmail() : dto.getEmail());
		Users.setPassword(
				dto.getPassword() == null ? Users.getPassword() : dto.getPassword());
		UsersRepository.save(Users);
	}

	// oracle db -> flashback technologies
	// softdelete
	@Override
	public void deleteUsers(String UsersId) {
		// 1 select data
		// 2 delete
		// or
		// 1 delete (harddelete)
//		UsersRepository.deleteById(UsersId);
		Users Users = UsersRepository.findBySecureId(UsersId)
				.orElseThrow(() -> new BadRequestException("invalid.UsersId"));
		UsersRepository.delete(Users);
		// softdelete
		// 1. select data deleted=false
//		Users Users = UsersRepository.findByIdAndDeletedFalse(UsersId)
//				.orElseThrow(() -> new BadRequestException("invalid.UsersId"));
//
//		// 2. update deleted=true
//		Users.setDeleted(Boolean.TRUE);
//		UsersRepository.save(Users);
	}

	@Override
	public List<Users> findUsers(List<String> UsersIdList) {
		List<Users> Userss = UsersRepository.findBySecureIdIn(UsersIdList);
		if (Userss.isEmpty())
			throw new BadRequestException("Users cant empty");
		return Userss;
	}

	@Override
	public List<UsersResponseDTO> constructDTO(List<Users> Users) {
		return Users.stream().map((u)->{
			UsersResponseDTO dto = new UsersResponseDTO();
			dto.setUsername(u.getUsername());
			dto.setEmail(u.getEmail());
			dto.setPassword(u.getPassword());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void createAndUpdateUsers(UsersCreateRequestDTO dto) {
		Users Users =  UsersRepository.findByUsername(dto.getUsername()).orElse(new Users());
		if(Users.getUsername()==null) {
			 Users.setUsername(dto.getUsername().toLowerCase()); //new 
		 }
		Users.setUsername(dto.getUsername());
		Users.setEmail(dto.getEmail());
		Users.setPassword(dto.getPassword());
		 
		 UsersRepository.save(Users);
	}

	@Override
	public ResultPageResponseDTO<UsersResponseDTO> findUsersList(Integer pages, Integer limit, String sortBy,
			String direction, String username) {
		username =  StringUtils.isEmpty(username) ? "%":username+"%";
		Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
		Pageable pageable = PageRequest.of(pages, limit, sort);
		Page<Users> pageResult =  UsersRepository.findByUsernameLikeIgnoreCase(username, pageable);
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
