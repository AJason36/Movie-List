package com.nostratech.movie.service;

import java.util.List;

import com.nostratech.movie.domain.Users;
import com.nostratech.movie.dto.UsersCreateRequestDTO;
import com.nostratech.movie.dto.UsersResponseDTO;
import com.nostratech.movie.dto.UsersUpdateRequestDTO;
import com.nostratech.movie.dto.ResultPageResponseDTO;


public interface UsersService {
	
	public UsersResponseDTO findUsersById(String id);
	
	public void createNewUsers(List<UsersCreateRequestDTO> dto);
	
	public void updateUsers(String UsersId, UsersUpdateRequestDTO dto);
	
	public void deleteUsers(String UsersId);

	public List<Users> findUsers(List<String> UsersIdList);
	
	public List<UsersResponseDTO> constructDTO(List<Users> Userss);

	public void createAndUpdateUsers(UsersCreateRequestDTO dto);
	
	public ResultPageResponseDTO<UsersResponseDTO> findUsersList(Integer pages, 
			Integer limit, String sortBy, String direction, String UsersName);
	
}
