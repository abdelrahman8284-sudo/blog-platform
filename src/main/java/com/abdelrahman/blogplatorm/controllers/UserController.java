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

import com.abdelrahman.blogplatorm.dtos.requests.UserRequestDto;
import com.abdelrahman.blogplatorm.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> createAccount(@RequestBody UserRequestDto dto){
		return ResponseEntity.ok(userService.insert(dto));
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,@RequestBody UserRequestDto dto){
		return ResponseEntity.ok(userService.update(id,dto));
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return ResponseEntity.ok(userService.findById(id));
	}
	@GetMapping("/email")
	public ResponseEntity<?> findByEmail(@RequestParam String email){
		return ResponseEntity.ok(userService.findByEmail(email));
	}
	@GetMapping
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(userService.findAll());
	}
}
