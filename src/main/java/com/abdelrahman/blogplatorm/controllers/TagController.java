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

import com.abdelrahman.blogplatorm.dtos.requests.TagRequestDto;
import com.abdelrahman.blogplatorm.services.TagService;
@RestController
@RequestMapping("/tag")
public class TagController {
	@Autowired
	private TagService tagService;
	
	@PostMapping
	public ResponseEntity<?> createTag(@RequestBody TagRequestDto tag){
		return ResponseEntity.ok(tagService.insert(tag));
	}
	@PutMapping
	public ResponseEntity<?> update(@PathVariable Long id,@RequestBody TagRequestDto tag){
		return ResponseEntity.ok(tagService.update(id,tag));
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return ResponseEntity.ok(tagService.findById(id));
	}
	
	@GetMapping("/name")
	public ResponseEntity<?> findByName(@RequestParam String name){
		return ResponseEntity.ok(tagService.findByName(name));
	}
	@GetMapping
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(tagService.findAll());
	}
}
