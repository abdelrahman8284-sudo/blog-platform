package com.abdelrahman.blogplatorm.controllers;

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

import com.abdelrahman.blogplatorm.dtos.requests.TagRequestDto;
import com.abdelrahman.blogplatorm.services.TagService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
@Tag(name = "Tag Management")
public class TagController {
	
	private final TagService tagService;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN','AUTHOR')")
	@Operation(summary = "Create Tag")
	public ResponseEntity<?> createTag(@RequestBody TagRequestDto tag){
		return ResponseEntity.ok(tagService.insert(tag));
	}
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Update Tag")
	public ResponseEntity<?> update(@PathVariable Long id,@RequestBody TagRequestDto tag){
		return ResponseEntity.ok(tagService.update(id,tag));
	}
	@GetMapping("/{id}")
	@Operation(summary = "Find Tag by its Id")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return ResponseEntity.ok(tagService.findById(id));
	}
	
	@GetMapping("/search")
	@Operation(summary = "Search Tags by Name")
	public ResponseEntity<?> findByName(@RequestParam String name){
		return ResponseEntity.ok(tagService.findByName(name));
	}
	@GetMapping
	@Operation(summary = "List All tags names")
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(tagService.findAll());
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Delete Tag by its id")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        // الـ Service عندك فيها findById ممكن نستخدمها للتأكد قبل الحذف
        tagService.delete(id); 
        return ResponseEntity.noContent().build();
    }
}
