package com.nostratech.movie.domain;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "review")
// @DynamicUpdate
@SQLDelete(sql = "UPDATE review SET deleted = true WHERE id = ?")
@Where(clause = "deleted=false")
public class Review extends AbstractBaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6538045629937300861L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_generator")
    @SequenceGenerator (name = "review_generator", sequenceName = "review_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(name = "comment", columnDefinition = "varchar(255)")
    private String comment;

    @Column(name = "star")
    private int star;
}
