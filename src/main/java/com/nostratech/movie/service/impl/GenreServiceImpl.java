package com.nostratech.movie.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nostratech.movie.domain.Genres;
import com.nostratech.movie.dto.GenreCreateRequestDTO;
import com.nostratech.movie.dto.GenreQueryDTO;
import com.nostratech.movie.dto.GenreResponseDTO;
import com.nostratech.movie.dto.GenreUpdateRequestDTO;
import com.nostratech.movie.dto.ResultPageResponseDTO;
import com.nostratech.movie.exception.BadRequestException;
import com.nostratech.movie.repository.GenreRepository;
import com.nostratech.movie.service.GenreService;
import com.nostratech.movie.util.PaginationUtil;

import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
// @SecurityRequirement(name="bearerAuth")
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    public GenreResponseDTO findGenreById(String genreId) {
        Genres genre = genreRepository.findBySecureId(genreId)
                .orElseThrow(() -> new BadRequestException("invalid.genreId"));
        GenreResponseDTO dto = new GenreResponseDTO();
        dto.setGenre(genre.getGenre());
        return dto;
    }

    public List<Genres> findGenres(List<String> genreIdList) {
        List<Genres> genres = genreRepository.findBySecureIdIn(genreIdList);
		if (genres.isEmpty())
			throw new BadRequestException("genre cant empty");
		return genres;
    }
    public void createNewGenre(GenreCreateRequestDTO dto) {
        Genres genre = new Genres();
        genre.setGenre(dto.getGenre());
        genreRepository.save(genre);
    }
	
    public void updateGenre(String genreId, GenreUpdateRequestDTO dto) {
        Genres genre = genreRepository.findBySecureId(genreId)
                .orElseThrow(() -> new BadRequestException("invalid.genreId"));
        genre.setGenre(dto.getGenre() == null ? genre.getGenre() : dto.getGenre());
        genreRepository.save(genre);
    }
	
    public void deleteGenre(String genreId) {
        Genres genre = genreRepository.findBySecureId(genreId)
                .orElseThrow(() -> new BadRequestException("invalid.genreId"));
        if (!genre.getMoviesGenre().isEmpty()) {
            throw new BadRequestException("Cannot delete the genre as it is associated with movies");
        }        
        genreRepository.delete(genre);
    }

	public ResultPageResponseDTO<GenreResponseDTO> findGenreList(Integer pages, 
            Integer limit, String sortBy, String direction, String genre) {
        genre = StringUtils.isEmpty(genre) ? "%" : genre + "%";
        Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);
        Page<Genres> pageResult = genreRepository.findByGenreLikeIgnoreCase(genre, pageable);
        List<GenreResponseDTO> dtos = pageResult.stream().map((g)->{
            GenreResponseDTO dto = new GenreResponseDTO();
            dto.setGenre(g.getGenre());
            return dto;
        }).collect(Collectors.toList());
        return PaginationUtil.createResultPageDTO(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
    }
	
    public List<GenreResponseDTO> constructDTO(List<Genres> genre) {
        return genre.stream().map((g)->{
            GenreResponseDTO dto = new GenreResponseDTO();
            dto.setGenre(g.getGenre());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public Map<Long, List<String>> findGenreMaps(List<Long> movieIdList) {
        List<GenreQueryDTO> queryList =  genreRepository.findGenreByMovieIdList(movieIdList);
		Map<Long, List<String>> genreMaps = new HashMap<>();
		List<String> genreNameList = null;
		for(GenreQueryDTO q:queryList) {
			if(!genreMaps.containsKey(q.getMovieId())) {
				genreNameList = new ArrayList<>();
			}else {
				genreNameList = genreMaps.get(q.getMovieId());
			}
			genreNameList.add(q.getGenre());
			genreMaps.put(q.getMovieId(), genreNameList);
		}
		return genreMaps;
    }
	
}
