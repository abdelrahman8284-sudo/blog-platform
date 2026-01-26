package com.abdelrahman.blogplatorm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abdelrahman.blogplatorm.dtos.requests.UserRequestDto;
import com.abdelrahman.blogplatorm.dtos.responses.UserResponseDto;
import com.abdelrahman.blogplatorm.entities.User;
import com.abdelrahman.blogplatorm.mappers.UserMapper;
import com.abdelrahman.blogplatorm.repositories.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	
	private final UserRepo userRepo;
	private final UserMapper mapper;
	
	public UserResponseDto insert(UserRequestDto dto) {
		User user = mapper.toUserEntity(dto);
		return mapper.toUserDto(userRepo.save(user));
	}
	
	public UserResponseDto update(Long id,UserRequestDto dto) {
		User currentUser = userRepo.findById(id).orElseThrow(()->new RuntimeException("User Not found"));
		User user = mapper.toUserEntity(dto);
		currentUser.setName(user.getName());
		currentUser.setEmail(user.getEmail());
		currentUser.setPassword(user.getPassword());
		return mapper.toUserDto(userRepo.save(currentUser));
	}
	
	public List<UserResponseDto> findAll(){
		
		return mapper.toList(userRepo.findAll());
	}
	
	public UserResponseDto findById(Long id) {
		return mapper.toUserDto(userRepo.findById(id).orElseThrow(()->new RuntimeException("User Not Found")));
	}
	
	public UserResponseDto findByEmail(String email) {
		return mapper.toUserDto(userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not Found")));
	}
	
	public void delete(Long id) {
		if(!userRepo.existsById(id))
			throw new RuntimeException("User Not Found");
		userRepo.deleteById(id);
	}
}
