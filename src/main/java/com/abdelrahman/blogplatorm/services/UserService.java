package com.abdelrahman.blogplatorm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdelrahman.blogplatorm.entities.User;
import com.abdelrahman.blogplatorm.repositories.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	public User insert(User user) {
		return userRepo.save(user);
	}
	
	public User update(Long id,User user) {
		User currentUser = userRepo.findById(id).orElseThrow(()->new RuntimeException("User Not found"));
		currentUser.setName(user.getName());
		currentUser.setEmail(user.getEmail());
		currentUser.setPassword(user.getPassword());
		return userRepo.save(user);
	}
	
	public List<User> findAll(){
		return userRepo.findAll();
	}
	
	public User findById(Long id) {
		return userRepo.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
	}
	
	public User findByEmail(String email) {
		return userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not Found"));
	}
	
	public void delete(Long id) {
		userRepo.deleteById(id);
	}
}
