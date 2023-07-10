package com.nostratech.movie.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Movie {
	private Long id;
    private Cinema cinema;
    private List<Review> reviews = new ArrayList<>();
    private List<Actor> actors = new ArrayList<>();
    private String title;
    private String genre;
    
    public void addActor(Actor actor) {
        actors.add(actor);
    }
    
    public void addReview(Review review) {
        reviews.add(review);
    }
}