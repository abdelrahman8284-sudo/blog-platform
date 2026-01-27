package com.abdelrahman.blogplatorm.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.abdelrahman.blogplatorm.dtos.responses.UserResponseDto;
import com.abdelrahman.blogplatorm.enums.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="posts")
@Builder
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor@AllArgsConstructor@Setter@Getter
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String title;
	
	@Lob
	private String content;
	
	private Integer readingTime;
	@ColumnDefault("DRAFT")
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@CreatedDate
	private LocalDateTime createdAt;
	
	@LastModifiedDate
	private LocalDateTime updatedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonManagedReference
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="category_id")
	@JsonManagedReference
	private Category category;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(inverseJoinColumns = @JoinColumn(name="tag_id"),joinColumns = @JoinColumn(name="post_id"),name="posts_tags")	
	@JsonManagedReference
	private Set<Tag> tags = new HashSet<>();
	
//	@PostPersist
//	protected void calcReadingTime() {
//		int words = this.getContent().trim().split("\\s+").length;
//		this.readingTime = (int)Math.ceil(words/200);
//	}
//	
	@PrePersist
	@PreUpdate
	protected void calcReadingTime() {
	    if (this.content != null && !this.content.isEmpty()) {
	        // بنقسم على 200.0 عشان نضمن إنها قسمة decimal والكسر م يضيعش
	        double words = this.content.trim().split("\\s+").length;
	        this.readingTime = (int) Math.ceil(words / 200.0);
	    } else {
	        this.readingTime = 0;
	    }
	}
}
