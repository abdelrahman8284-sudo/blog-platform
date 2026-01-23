package com.abdelrahman.blogplatorm.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Users")
@NoArgsConstructor@AllArgsConstructor@Setter@Getter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Email
	@NotBlank
	@Column(nullable = false)
	private String email;
	@NotBlank
	@Column(nullable = false)
	private String password;
	
	@CreatedDate
	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy = "user")
	@JsonBackReference
	private List<Post> posts;
	
}
