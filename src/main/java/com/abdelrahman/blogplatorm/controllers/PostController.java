package com.abdelrahman.blogplatorm.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abdelrahman.blogplatorm.dtos.requests.PostRequestDto;
import com.abdelrahman.blogplatorm.dtos.update.PostUpdateDto;
import com.abdelrahman.blogplatorm.services.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	
	@PostMapping
	public ResponseEntity<?> addPost(@RequestBody@Valid PostRequestDto dto){
		return ResponseEntity.ok(postService.insert(dto));
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,@Valid@RequestBody PostUpdateDto post){
		return ResponseEntity.ok(postService.update(id,post));
	}
	@PutMapping("/publish/{id}")
	public ResponseEntity<?> publish(@PathVariable Long id){
		return ResponseEntity.ok(postService.publishPost(id));
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return ResponseEntity.ok(postService.findById(id));
	}
	@GetMapping("/search")
	public ResponseEntity<?> findByTitle(@RequestParam String title){
		return ResponseEntity.ok(postService.findByTitle(title));
	}
	@GetMapping
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(postService.findAll());
	} 
	
	@GetMapping("/drafts")
	public ResponseEntity<?> findDrafts(){
		return ResponseEntity.ok(postService.findAll());
	}
}
