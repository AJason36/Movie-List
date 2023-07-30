package com.nostratech.movie.domain;

import java.util.List;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie")
// @DynamicUpdate
@SQLDelete(sql = "UPDATE movie SET deleted = true WHERE id = ?")
@Where(clause = "deleted=false")
public class Movie extends AbstractBaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2957072252990334899L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_generator")
    @SequenceGenerator (name = "movie_generator", sequenceName = "movie_id_seq")
    private Long id;
    
    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    private String genre;

    @ManyToMany(mappedBy = "moviesDirected")
    private List<Person> directors;

    @ManyToMany(mappedBy = "moviesActed")
    private List<Person> actors;

    @OneToMany(mappedBy = "movie")
    private List<Review> reviews;
    
    // public void addActor(Actor actor) {
    //     actors.add(actor);
    // }
    
    // public void addReview(Review review) {
    //     reviews.add(review);
    // }
}