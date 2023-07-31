package com.nostratech.movie.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.StringJoiner;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nostratech.movie.service.GenreService;
import com.nostratech.movie.service.MovieService;
import com.nostratech.movie.service.PersonService;
import com.nostratech.movie.service.ReviewService;
import com.nostratech.movie.util.PaginationUtil;
import com.nostratech.movie.domain.Genres;
import com.nostratech.movie.domain.Movie;
import com.nostratech.movie.domain.Person;
import com.nostratech.movie.domain.Review;
import com.nostratech.movie.dto.MovieCreateDTO;
import com.nostratech.movie.dto.MovieDetailDTO;
import com.nostratech.movie.dto.MovieUpdateRequestDTO;
import com.nostratech.movie.dto.ResultPageResponseDTO;
import com.nostratech.movie.exception.BadRequestException;
import com.nostratech.movie.repository.MovieRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service("movieService")
public class MovieServiceImpl implements MovieService {

	private final MovieRepository movieRepository;
	private final PersonService actorService;
	private final PersonService directorService;
	private final GenreService genreService;

	@Override
	public MovieDetailDTO findMovieDetailById(String movieId) {
		Movie movie = movieRepository.findBySecureId(movieId)
				.orElseThrow(() -> new BadRequestException("invalid.movieId"));
		MovieDetailDTO dto = new MovieDetailDTO();
		dto.setMovieId(movie.getSecureId());
		dto.setGenres(genreService.constructDTO(movie.getGenreList()));
		dto.setTitle(movie.getTitle());
		dto.setActors(actorService.constructDTO(movie.getActors()));
		dto.setDirectors(directorService.constructDTO(movie.getDirectors()));
		return dto;
	}

	@Override
	public List<MovieDetailDTO> findMovieListDetail() {
		List<Movie> movies = movieRepository.findAll();
		return movies.stream().map((m) -> {
			MovieDetailDTO dto = new MovieDetailDTO();
			dto.setTitle(m.getTitle());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void createNewMovie(MovieCreateDTO dto) {
		Movie movie = new Movie();
		List<Person> actors = actorService.findPersons(dto.getActorIdList());
		List<Person> directors = directorService.findPersons(dto.getDirectorIdList());
		List<Genres> genres = genreService.findGenres(dto.getGenreIdList());

		movie.setTitle(dto.getTitle());
		movie.setActors(actors);
		movie.setDirectors(directors);
		movie.setGenreList(genres);
		movieRepository.save(movie);
	}

	@Override
	public void updateMovie(String movieId, MovieUpdateRequestDTO dto) {
		// get Movie from repository
		Movie movie = movieRepository.findBySecureId(movieId)
				.orElseThrow(() -> new BadRequestException("invalid.movieId"));
		// update
		movie.setTitle(dto.getTitle());
		// save
		movieRepository.save(movie);

	}

	@Override
	public void deleteMovie(String movieId) {
		Movie movie = movieRepository.findBySecureId(movieId)
				.orElseThrow(() -> new BadRequestException("invalid.movieId"));
		movieRepository.delete(movie);
	}

	@Override
	public ResultPageResponseDTO<MovieDetailDTO> findMovieList(Integer pages, Integer limit, String sortBy,
			String direction, String movieTitle) {
		movieTitle = StringUtils.isEmpty(movieTitle) ? "%" : movieTitle + "%";
		Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
		Pageable pageable = PageRequest.of(pages, limit, sort);
		Page<Movie> pageResult = movieRepository.findByTitleLikeIgnoreCase(movieTitle, pageable);
		List<MovieDetailDTO> dtos = pageResult.stream().map((m) -> {
			MovieDetailDTO dto = new MovieDetailDTO();
			dto.setTitle(m.getTitle());
			return dto;
		}).collect(Collectors.toList());
		return PaginationUtil.createResultPageDTO(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
	}
	
	@Override
	public MovieDetailDTO constructDTO(Movie movie) {
		MovieDetailDTO dto = new MovieDetailDTO();
		dto.setMovieId(movie.getSecureId());
		dto.setTitle(movie.getTitle());
		dto.setActors(actorService.constructDTO(movie.getActors()));
		dto.setDirectors(directorService.constructDTO(movie.getDirectors()));
		return dto;
	}
}
