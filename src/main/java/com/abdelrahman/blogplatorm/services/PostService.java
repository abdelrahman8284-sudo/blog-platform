package com.abdelrahman.blogplatorm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdelrahman.blogplatorm.entities.Post;
import com.abdelrahman.blogplatorm.repositories.PostRepo;

@Service
public class PostService {
	@Autowired
	private PostRepo postRepo;

	public Post insert(Post post) {
		return postRepo.save(post);
	}

	public Post update(Long id, Post post) {
		Optional<Post> currentPost = postRepo.findById(id);
		if(currentPost.isEmpty()) {
			throw new RuntimeException("Post Not found");
		}
		return postRepo.save(post);
	}

	public List<Post> findAll() {
		return postRepo.findAll();
	}

	public Post findById(Long id) {
		return postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post Not Found"));
	}
	
	public List<Post> findByTitle(String title) {
		
		return postRepo.findByTitle(title);
	}

	public String delete(Long id) {
		if(postRepo.findById(id).isEmpty()) {
			return "This post is not found to delete";
		}
		else {
			postRepo.deleteById(id);
			return "This post deleted successfully";
		}
	}

	
}