package com.abdelrahman.blogplatorm.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdelrahman.blogplatorm.entities.Post;
import com.abdelrahman.blogplatorm.enums.Status;
@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
	List<Post> findByTitle(String title,Status status);
	
	Optional<Post> findByIdAndStatus(Long id,Status status);
	
	List<Post> findAllByStatus(Status status);
	
}
