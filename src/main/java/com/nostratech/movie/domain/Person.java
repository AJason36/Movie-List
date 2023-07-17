package com.nostratech.movie.domain;

import java.util.List;

import org.hibernate.annotations.DynamicUpdate;
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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
// @DynamicUpdate
@SQLDelete(sql = "UPDATE person SET deleted = true WHERE id = ?")
@Where(clause = "deleted=false")
public class Person extends AbstractBaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6916219470422439112L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_generator")
    @SequenceGenerator (name = "person_generator", sequenceName = "person_id_seq")
    private Long id;

    @Column(name = "person_name", nullable=false,columnDefinition = "varchar(300)")
    private String name;

    @Column(name = "age", nullable=false)
    private Integer age;

    @ManyToMany
    @JoinTable(
            name = "actor",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> moviesActed;

    @ManyToMany
    @JoinTable(
            name = "director",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> moviesDirected;
}