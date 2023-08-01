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
@Table(name = "genres")
@SQLDelete(sql = "UPDATE genres SET deleted = true WHERE id = ?")
@Where(clause = "deleted=false")
public class Genres extends AbstractBaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "genre", unique=true, nullable=false)
    private String genre;

    @ManyToMany(mappedBy="genre")
    private List<Movie> moviesGenre;
}
