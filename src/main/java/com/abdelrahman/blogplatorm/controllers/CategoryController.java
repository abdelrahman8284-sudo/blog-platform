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

import com.abdelrahman.blogplatorm.dtos.requests.CategoryRequestDto;
import com.abdelrahman.blogplatorm.dtos.responses.CategoryResponseDto;
import com.abdelrahman.blogplatorm.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Tag(name = "Category Management")
public class CategoryController {
	
	private final CategoryService categoryService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Create Category (Admin only)")
	public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody@Valid CategoryRequestDto category){
		return new ResponseEntity<>(
				categoryService.insert(category)
				,HttpStatus.CREATED
		);
	}
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Update Category (Admin only)")
	public ResponseEntity<?> update(@PathVariable Long id,@RequestBody@Valid CategoryRequestDto category){
		return ResponseEntity.ok(categoryService.update(id,category));
	}
	@GetMapping("/{id}")
	@Operation(summary = "Find Category by Id")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return ResponseEntity.ok(categoryService.findById(id));
	}
	
	@GetMapping("/search")
	@Operation(summary = "Find Category by name")
	public ResponseEntity<?> findByName(@RequestParam@Valid String name){
		return ResponseEntity.ok(categoryService.findByName(name));
	}
//	@GetMapping
//	public ResponseEntity<?> findAll(){
//		return ResponseEntity.ok(categoryService.findAll());
//	}
	@GetMapping("/all")
	@Operation(summary = "Find All Category and there post count")
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(categoryService.listCategories());
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete Category by Id (Admin only)")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
