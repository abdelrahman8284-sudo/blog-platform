package com.abdelrahman.blogplatorm.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abdelrahman.blogplatorm.dtos.requests.PostRequestDto;
import com.abdelrahman.blogplatorm.dtos.responses.PostResponseDto;
import com.abdelrahman.blogplatorm.dtos.update.PostUpdateDto;
import com.abdelrahman.blogplatorm.services.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Tag(name = "Post Management")
public class PostController {

	private final PostService postService;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN','AUTHOR')")
	@Operation(summary = "Add post(draft)")
	public ResponseEntity<PostResponseDto> addPost(@RequestBody@Valid PostRequestDto dto){
		
		return new ResponseEntity<>(postService.insert(dto),HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','AUTHOR')")
	@Operation(summary = "Update Post")
	public ResponseEntity<?> update(@PathVariable Long id,@Valid@RequestBody PostUpdateDto post){
		return ResponseEntity.ok(postService.update(id,post));
	}
	@PutMapping("/publish/{id}")
	@PreAuthorize("hasAnyRole('AUTHOR','ADMIN')")
	@Operation(summary = "Publish Post")
	public ResponseEntity<?> publish(@PathVariable Long id){
		return ResponseEntity.ok(postService.publishPost(id));
	}
	@GetMapping("/{id}")
	@Operation(summary = "Find Post by Id")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return ResponseEntity.ok(postService.findById(id));
	}
	@GetMapping("/search")
	@Operation(summary = "Find Post by its Title")
	public ResponseEntity<?> findByTitle(@RequestParam String title){
		return ResponseEntity.ok(postService.findByTitle(title));
	}
	@GetMapping
	@Operation(summary = "Find All Posts")
	public ResponseEntity<?> findAll(
			@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "5") int pageSize,
			@RequestParam(defaultValue = "createdAt") String sortBy,
			@RequestParam(defaultValue = "ASC") String sortType
			){
		return ResponseEntity.ok(postService.findAll(pageNumber, pageSize, sortBy, sortType));
	} 
	// تعديل ان لازم ال AUTHOR الي يشوف ده يشوف الخاص بيه بس
	@GetMapping("/drafts")
	@PreAuthorize("hasAnyRole('AUTHOR','ADMIN')")
	@Operation(summary = "Get draft posts for current authenticated user")
	public ResponseEntity<?> findDrafts() {
	    return ResponseEntity.ok(postService.findDraftsForCurrentUser());
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('AUTHOR','ADMIN')")
	@Operation(summary = "Delete Post by id")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		postService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
