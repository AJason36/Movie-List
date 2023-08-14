package com.nostratech.movie.service;

import java.util.List;
import java.util.Map;

import com.nostratech.movie.domain.Genres;
import com.nostratech.movie.dto.GenreCreateRequestDTO;
import com.nostratech.movie.dto.GenreResponseDTO;
import com.nostratech.movie.dto.GenreUpdateRequestDTO;
import com.nostratech.movie.dto.ResultPageResponseDTO;


public interface GenreService {
	
	public GenreResponseDTO findGenreById(String genreId);
	
	public List<Genres> findGenres(List<String> genreIdList);
	
	public void createNewGenre(GenreCreateRequestDTO dto);
	
	public void updateGenre(String genreId, GenreUpdateRequestDTO dto);
	
	public void deleteGenre(String genreId);

	public ResultPageResponseDTO<GenreResponseDTO> findGenreList(Integer pages, 
			Integer limit, String sortBy, String direction, String genre);
	
	public List<GenreResponseDTO> constructDTO(List<Genres> genre);
	
	public Map<Long, List<String>> findGenreMaps(List<Long> movieIdList);

}
