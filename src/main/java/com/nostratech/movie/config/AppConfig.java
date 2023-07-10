package com.nostratech.movie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nostratech.movie.domain.Actor;
import com.nostratech.movie.domain.Cinema;
import com.nostratech.movie.domain.Movie;
import com.nostratech.movie.domain.Review;
import com.nostratech.movie.repository.MovieRepository;
import com.nostratech.movie.repository.impl.MovieRepositoryImpl;


@Configuration
public class AppConfig {
	
	@Bean
    public Movie movie( Cinema cinema,  Review review1,  Review review2,   Actor actor1,  Actor actor2) {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Lost Treasure");
        movie.setGenre("Adventure");
        movie.setCinema(cinema);

        List<Review> reviews = new ArrayList<>();
        reviews.add(review1);
        reviews.add(review2);

        movie.setReviews(reviews);

        List<Actor> actors = new ArrayList<>();
        actors.add(actor1);
        actors.add(actor2);

        movie.setActors(actors);

        return movie;
    }

    @Bean
    public Cinema cinema() {
    	Cinema cinema = new Cinema();
    	cinema.setName("XXI");

        return cinema;
    }

    @Bean
    public Review review1() {
    	Review review1 = new Review();
        review1.setReview("abcd");
        review1.setStar(4);
        return review1;
    }
    
    @Bean
    public Review review2() {
    	Review review2 = new Review();
        review2.setReview("efgh");
        review2.setStar(5);
        return review2;
    }

    @Bean
    public Actor actor1() {
    	Actor actor1 = new Actor();
        actor1.setName("James Bond");
        return actor1;
    }
    @Bean
    public Actor actor2() {
        Actor actor2 = new Actor();
        actor2.setName("Jackie Chan");
        return actor2;
    }
    
    @Bean
	public MovieRepository MovieRepository(Movie movie) {
		Map<Long, Movie> MovieMap = new HashMap<Long, Movie>();
		MovieMap.put(1L, movie);		
		MovieRepositoryImpl MovieRepository = new MovieRepositoryImpl();
		MovieRepository.setMovieMap(MovieMap);
		
		return MovieRepository;
	}
     
}