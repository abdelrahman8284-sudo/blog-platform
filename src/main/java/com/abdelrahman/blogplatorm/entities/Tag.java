package com.abdelrahman.blogplatorm.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="tags")
@Entity
@Builder
@Setter@Getter@AllArgsConstructor@NoArgsConstructor
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false,unique = true)
	private String name;
	
	@ManyToMany(mappedBy = "tags")
	@JsonBackReference
	private List<Post> posts = new ArrayList<>();

	@Override
	public int hashCode() {
		return Objects.hash(id, name, posts);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(posts, other.posts);
	}
	
	
}
