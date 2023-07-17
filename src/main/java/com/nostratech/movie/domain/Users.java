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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
// @DynamicUpdate
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ?")
@Where(clause = "deleted=false")
public class Users extends AbstractBaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6747608909744931207L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator (name = "user_generator", sequenceName = "user_id_seq")
    private Long id;

    @Column(name = "user_name", nullable=false)
    private String username;

    @Column(name = "email", nullable=false)
    private String email;

    @Column(name = "password", nullable=false)
    private String password;
}
