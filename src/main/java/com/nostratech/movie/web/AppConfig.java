package com.nostratech.movie.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nostratech.movie.dto.Actor;
import com.nostratech.movie.dto.Cinema;
import com.nostratech.movie.dto.Movie;
import com.nostratech.movie.dto.Review;

@RestController
@Configuration
public class AppConfig {
	
	@Bean
	@GetMapping("/movie")
    public Movie movie(@PathVariable Cinema cinema, @PathVariable Review review1, @PathVariable Review review2, @PathVariable  Actor actor1, @PathVariable Actor actor2) {
        Movie movie = new Movie(cinema);
        movie.setTitle("Lost Treasure");
        movie.setGenre("Adventure");

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
	
	@PostMapping("/movie")
	public Movie createMovie(@RequestBody String title, Cinema cinema) {
		Movie movie = new Movie(cinema);
		movie.setTitle(title);
		
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
        review1.setUsername("abcd");
        review1.setStar(4);
        return review1;
    }
    
    @Bean
    public Review review2() {
    	Review review2 = new Review();
        review2.setUsername("efgh");
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
     
}