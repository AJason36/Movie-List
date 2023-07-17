package com.nostratech.movie.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.StringJoiner;


import org.springframework.stereotype.Service;

import com.nostratech.movie.service.MovieService;
import com.nostratech.movie.domain.Person;
import com.nostratech.movie.domain.Review;
import com.nostratech.movie.domain.Movie;
import com.nostratech.movie.dto.MovieCreateDTO;
import com.nostratech.movie.dto.MovieDetailDTO;
import com.nostratech.movie.dto.MovieUpdateRequestDTO;
import com.nostratech.movie.exception.BadRequestException;
import com.nostratech.movie.repository.MovieRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service("movieService")
public class MovieServiceImpl implements MovieService {

	private final MovieRepository MovieRepository;

	@Override
	public MovieDetailDTO findMovieDetailById(String movieId) {
		Movie movie = MovieRepository.findBySecureId(movieId)
				.orElseThrow(() -> new BadRequestException("invalid.movieId"));
		MovieDetailDTO dto = new MovieDetailDTO();
		List<Person> Persons = movie.getActors();
		StringJoiner joiner = new StringJoiner(", ");
		for (Person Person : Persons) {
			joiner.add(Person.getName());
		}
		String sPerson = joiner.toString();

		List<Review> Reviews = movie.getReviews();
		StringJoiner joiner2 = new StringJoiner(", ");
		for (Review Review : Reviews) {
			joiner2.add(Review.getComment());
		}
		String sReview = joiner2.toString();
		dto.setMovieId(movie.getId());
		dto.setGenre(movie.getGenre());
		dto.setActor(sPerson);
		dto.setReview(sReview);
		dto.setMovieTitle(movie.getTitle());
		return dto;
	}

	@Override
	public List<MovieDetailDTO> findMovieListDetail() {
		List<Movie> Movies = MovieRepository.findAll();
		return Movies.stream().map((m) -> {
			MovieDetailDTO dto = new MovieDetailDTO();
			List<Person> Persons = m.getActors();
			StringJoiner joiner = new StringJoiner(", ");
			for (Person Person : Persons) {
				joiner.add(Person.getName());
			}
			String sPerson = joiner.toString();

			List<Review> Reviews = m.getReviews();
			StringJoiner joiner2 = new StringJoiner(", ");
			for (Review Review : Reviews) {
				joiner2.add(Review.getComment());
			}
			String sReview = joiner2.toString();
			dto.setMovieId(m.getId());
			dto.setGenre(m.getGenre());
			dto.setActor(sPerson);
			dto.setReview(sReview);
			dto.setMovieTitle(m.getTitle());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void createNewMovie(MovieCreateDTO dto) {
		Person Person = new Person();
		Person.setName(dto.getActor());

		Review review = new Review();
		review.setComment(dto.getReview());

		Movie Movie = new Movie();

		Movie.setTitle(dto.getMovieTitle());
		Movie.setGenre(dto.getGenre());
		MovieRepository.save(Movie);
	}

	@Override
	public void updateMovie(String movieId, MovieUpdateRequestDTO dto) {
		// get Movie from repository
		Movie movie = MovieRepository.findBySecureId(movieId)
				.orElseThrow(() -> new BadRequestException("invalid.movieId"));
		// update
		movie.setTitle(dto.getMovieTitle());
		movie.setGenre(dto.getGenre());
		// save
		MovieRepository.save(movie);

	}

	@Override
	public void deleteMovie(String movieId) {
		Movie movie = MovieRepository.findBySecureId(movieId)
				.orElseThrow(() -> new BadRequestException("invalid.movieId"));
		MovieRepository.delete(movie);
	}

}
