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

import com.abdelrahman.blogplatorm.dtos.requests.CategoryRequestDto;
import com.abdelrahman.blogplatorm.services.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
	
	private final CategoryService categoryService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createCategory(@RequestBody@Valid CategoryRequestDto category){
		return ResponseEntity.ok(categoryService.insert(category));
	}
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@PathVariable Long id,@RequestBody CategoryRequestDto category){
		return ResponseEntity.ok(categoryService.update(id,category));
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return ResponseEntity.ok(categoryService.findById(id));
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> findByName(@RequestParam String name){
		return ResponseEntity.ok(categoryService.findByName(name));
	}
//	@GetMapping
//	public ResponseEntity<?> findAll(){
//		return ResponseEntity.ok(categoryService.findAll());
//	}
	@GetMapping("/all")
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(categoryService.listCategories());
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
