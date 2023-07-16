package com.nostratech.movie.domain;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "actor")
// @DynamicUpdate
@SQLDelete(sql = "UPDATE actor SET deleted = true WHERE id = ?")
@Where(clause = "deleted=false")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "actor_name", nullable=false,columnDefinition = "varchar(255)")
    private String name;

    @Column(name = "age", nullable=false)
    private Integer age;
    
    @Column(name="deleted", columnDefinition = "boolean default false")
	private Boolean deleted;
}