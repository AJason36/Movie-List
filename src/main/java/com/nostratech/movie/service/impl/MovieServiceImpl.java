package com.nostratech.movie.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.StringJoiner;


import org.springframework.stereotype.Service;

import com.nostratech.movie.service.MovieService;
import com.nostratech.movie.domain.Actor;
import com.nostratech.movie.domain.Cinema;
import com.nostratech.movie.domain.Review;
import com.nostratech.movie.domain.Movie;
import com.nostratech.movie.dto.MovieCreateDTO;
import com.nostratech.movie.dto.MovieDetailDTO;
import com.nostratech.movie.dto.MovieUpdateRequestDTO;
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
	public MovieDetailDTO findMovieDetailById(Long movieId) {
		Movie Movie = MovieRepository.findMovieById(movieId);
		MovieDetailDTO dto = new MovieDetailDTO();
		List<Actor> actors = Movie.getActors();
		StringJoiner joiner = new StringJoiner(", ");
		for (Actor actor : actors) {
			joiner.add(actor.getName());
		}
		String sActor = joiner.toString();

		List<Review> Reviews = Movie.getReviews();
		StringJoiner joiner2 = new StringJoiner(", ");
		for (Review Review : Reviews) {
			joiner2.add(Review.getReview());
		}
		String sReview = joiner2.toString();
		dto.setMovieId(Movie.getId());
		dto.setGenre(Movie.getGenre());
		dto.setCinema(Movie.getCinema().getName());
		dto.setActor(sActor);
		dto.setReview(sReview);
		dto.setMovieTitle(Movie.getTitle());
		return dto;
	}

	@Override
	public List<MovieDetailDTO> findMovieListDetail() {
		List<Movie> Movies = MovieRepository.findAll();
		return Movies.stream().map((m) -> {
			MovieDetailDTO dto = new MovieDetailDTO();
			List<Actor> actors = m.getActors();
			StringJoiner joiner = new StringJoiner(", ");
			for (Actor actor : actors) {
				joiner.add(actor.getName());
			}
			String sActor = joiner.toString();

			List<Review> Reviews = m.getReviews();
			StringJoiner joiner2 = new StringJoiner(", ");
			for (Review Review : Reviews) {
				joiner2.add(Review.getReview());
			}
			String sReview = joiner2.toString();
			dto.setMovieId(m.getId());
			dto.setCinema(m.getCinema().getName());
			dto.setGenre(m.getGenre());
			dto.setActor(sActor);
			dto.setReview(sReview);
			dto.setMovieTitle(m.getTitle());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void createNewMovie(MovieCreateDTO dto) {
		Actor Actor = new Actor();
		Actor.setName(dto.getActor());

		Review review = new Review();
		review.setReview(dto.getReview());

		Cinema cinema = new Cinema();
		cinema.setName(dto.getCinema());
		Movie Movie = new Movie();

		Movie.addActor(Actor);
		Movie.setTitle(dto.getMovieTitle());
		Movie.setGenre(dto.getGenre());
		Movie.setCinema(cinema);
		Movie.addReview(review);
		MovieRepository.save(Movie);
	}

	@Override
	public void updateMovie(Long MovieId, MovieUpdateRequestDTO dto) {
		// get Movie from repository
		Movie Movie = MovieRepository.findMovieById(MovieId);
		// update
		Movie.setTitle(dto.getMovieTitle());
		// save
		MovieRepository.update(Movie);

	}

	@Override
	public void deleteMovie(Long MovieId) {
		MovieRepository.delete(MovieId);

	}

}
