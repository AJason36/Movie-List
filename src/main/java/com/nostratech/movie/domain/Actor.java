package com.nostratech.movie.domain;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// @Entity
// @Table(name = "actor")
// // @DynamicUpdate
// @SQLDelete(sql = "UPDATE actor SET deleted = true WHERE id = ?")
// @Where(clause = "deleted=false")
public class Actor extends AbstractBaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4968241443498797042L;

	// @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actor_generator")
    // @SequenceGenerator (name = "actor_generator", sequenceName = "actor_id_seq")
    private Long id;

    // @Column(name = "actor_name", nullable=false,columnDefinition = "varchar(255)")
    private String name;

    // @Column(name = "age", nullable=false)
    private Integer age;
}