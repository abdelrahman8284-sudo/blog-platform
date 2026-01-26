package com.abdelrahman.blogplatorm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abdelrahman.blogplatorm.dtos.requests.CategoryRequestDto;
import com.abdelrahman.blogplatorm.entities.Category;
import com.abdelrahman.blogplatorm.services.CategoryService;
@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<?> createCategory(@RequestBody CategoryRequestDto category){
		return ResponseEntity.ok(categoryService.insert(category));
	}
	@PutMapping
	public ResponseEntity<?> update(@PathVariable Long id,@RequestBody CategoryRequestDto category){
		return ResponseEntity.ok(categoryService.update(id,category));
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return ResponseEntity.ok(categoryService.findById(id));
	}
	
	@GetMapping("/name")
	public ResponseEntity<?> findByName(@RequestParam String name){
		return ResponseEntity.ok(categoryService.findByName(name));
	}
	@GetMapping
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(categoryService.findAll());
	}
}
