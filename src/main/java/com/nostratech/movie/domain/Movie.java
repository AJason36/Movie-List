package com.nostratech.movie.domain;

import java.util.List;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@SQLDelete(sql = "UPDATE movie SET deleted = true WHERE id = ?")
@Where(clause = "deleted=false")
public class Movie extends AbstractBaseEntity {
        /**
         * 
         */
        private static final long serialVersionUID = -2957072252990334899L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "title", nullable = false)
        private String title;

        @ManyToMany
        @JoinTable(name = "movies_genre", joinColumns = @JoinColumn(name = "movie_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "genre_id", nullable = false))
        private List<Genres> genre;

        @ManyToMany
        @JoinTable(name = "moviesDirected", joinColumns = @JoinColumn(name = "movie_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "person_id", nullable = false))
        private List<Person> directors;

        @ManyToMany
        @JoinTable(name = "moviesActed", joinColumns = @JoinColumn(name = "movie_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "person_id", nullable = false))
        private List<Person> actors;

        @OneToMany(mappedBy = "movie")
        private List<Review> reviews;
}