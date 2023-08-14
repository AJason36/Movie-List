package com.nostratech.movie.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nostratech.movie.service.GenreService;
import com.nostratech.movie.service.MovieService;
import com.nostratech.movie.service.PersonService;
import com.nostratech.movie.util.PaginationUtil;
import com.nostratech.movie.domain.Genres;
import com.nostratech.movie.domain.Movie;
import com.nostratech.movie.domain.Person;
import com.nostratech.movie.dto.GenreResponseDTO;
import com.nostratech.movie.dto.MovieCreateDTO;
import com.nostratech.movie.dto.MovieDetailDTO;
import com.nostratech.movie.dto.MovieListResponseDTO;
import com.nostratech.movie.dto.MovieQueryDTO;
import com.nostratech.movie.dto.MovieUpdateRequestDTO;
import com.nostratech.movie.dto.PersonResponseDTO;
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
		dto.setGenres(genreService.constructDTO(movie.getGenre()));
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
		movie.setGenre(genres);
		movieRepository.save(movie);
	}

	@Override
	public Movie findMovie(String movieId) {
		Optional<Movie> movieOptional = movieRepository.findBySecureId(movieId);
		if (movieOptional.isPresent()) {
			return movieOptional.get();
		} else {
			throw new BadRequestException("Movie not found");
		}
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
		// Remove the movie from the lists of actors and directors in the Person entity
		for (Person actor : movie.getActors()) {
			actor.getMoviesActed().remove(movie);
		}
		for (Person director : movie.getDirectors()) {
			director.getMoviesDirected().remove(movie);
		}

		// Remove the movie from the lists of moviesGenre in the Genres entity
		for (Genres genre : movie.getGenre()) {
			genre.getMoviesGenre().remove(movie);
		}
		movieRepository.delete(movie);
		// delete constraint/softdelete
	}

	@Override
	public ResultPageResponseDTO<MovieListResponseDTO> findMovieList(Integer pages, Integer limit, String sortBy,
			String direction, String movieTitle, String genre, String actorName, String directorName) {
		movieTitle = StringUtils.isEmpty(movieTitle) ? "%" : movieTitle + "%";
		Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
		Pageable pageable = PageRequest.of(pages, limit, sort);
		Page<MovieQueryDTO> pageResult = movieRepository.findMovieList(movieTitle, genre, actorName, directorName,
				pageable);
		List<Long> idList = pageResult.stream().map(m -> m.getMovieId()).collect(Collectors.toList());
		Map<Long, List<String>> genreMap = genreService.findGenreMaps(idList);
		Map<Long, List<String>> actorMap = actorService.findActorsMaps(idList);
		Map<Long, List<String>> directorMap = directorService.findDirectorsMaps(idList);
		List<MovieListResponseDTO> dtos = pageResult.stream().map((m) -> {
			MovieListResponseDTO dto = new MovieListResponseDTO();
			dto.setMovieId(m.getSecureId());
			dto.setTitle(m.getTitle());
			dto.setActors(actorMap.get(m.getMovieId()));
			dto.setDirectors(directorMap.get(m.getMovieId()));
			dto.setGenres(genreMap.get(m.getMovieId()));
			return dto;
		}).collect(Collectors.toList());
		return PaginationUtil.createResultPageDTO(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
	}

	// @Override
	// public MovieDetailDTO constructDTO(Movie movie) {
	// MovieDetailDTO dto = new MovieDetailDTO();
	// dto.setMovieId(movie.getSecureId());
	// dto.setTitle(movie.getTitle());
	// dto.setActors(actorService.constructDTO(movie.getActors()));
	// dto.setDirectors(directorService.constructDTO(movie.getDirectors()));
	// return dto;
	// }
}

// @Override
// public ResultPageResponseDTO<MovieDetailDTO> findMovieList(Integer pages,
// Integer limit, String sortBy,
// String direction, String movieTitle) {
// movieTitle = StringUtils.isEmpty(movieTitle) ? "%" : movieTitle + "%";
// Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction),
// sortBy));
// Pageable pageable = PageRequest.of(pages, limit, sort);
// Page<Movie> pageResult =
// movieRepository.findByTitleLikeIgnoreCase(movieTitle, pageable);
// List<MovieDetailDTO> dtos = pageResult.stream().map((m) -> {
// MovieDetailDTO dto = new MovieDetailDTO();
// dto.setMovieId(m.getSecureId()); // Set the movieId using the getId() method
// of the Movie entity
// dto.setTitle(m.getTitle());

// // Map the genres to a list of GenreResponseDTO
// List<GenreResponseDTO> genreDTOs = m.getGenre().stream().map(genre -> {
// GenreResponseDTO genreDTO = new GenreResponseDTO();
// genreDTO.setGenre(genre.getGenre());
// return genreDTO;
// }).collect(Collectors.toList());
// dto.setGenres(genreDTOs);

// // Map the actors to a list of PersonResponseDTO
// List<PersonResponseDTO> actorDTOs = m.getActors().stream().map(actor -> {
// PersonResponseDTO actorDTO = new PersonResponseDTO();
// actorDTO.setName(actor.getName());
// actorDTO.setAge(actor.getAge());
// return actorDTO;
// }).collect(Collectors.toList());
// dto.setActors(actorDTOs);

// // Map the directors to a list of PersonResponseDTO
// List<PersonResponseDTO> directorDTOs = m.getDirectors().stream().map(director
// -> {
// PersonResponseDTO directorDTO = new PersonResponseDTO();
// directorDTO.setName(director.getName());
// directorDTO.setAge(director.getAge());
// return directorDTO;
// }).collect(Collectors.toList());
// dto.setDirectors(directorDTOs);

// return dto;
// }).collect(Collectors.toList());
// return PaginationUtil.createResultPageDTO(dtos,
// pageResult.getTotalElements(), pageResult.getTotalPages());
// }