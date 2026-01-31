package com.abdelrahman.blogplatorm.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.abdelrahman.blogplatorm.dtos.requests.UserRequestDto;
import com.abdelrahman.blogplatorm.dtos.responses.UserResponseDto;
import com.abdelrahman.blogplatorm.entities.User;
import com.abdelrahman.blogplatorm.exceptions.RecordNotFoundException;
import com.abdelrahman.blogplatorm.mappers.UserMapper;
import com.abdelrahman.blogplatorm.repositories.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	
	private final UserRepo userRepo;
	private final UserMapper mapper;
	
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
	
	public UserResponseDto register(UserRequestDto dto) {
		User user = mapper.toUserEntity(dto);
		user.setPassword(encoder.encode(dto.getPassword()));
		return mapper.toUserDto(userRepo.save(user));
	}
	
	public UserResponseDto update(Long id,UserRequestDto dto) {
		User currentUser = userRepo.findById(id).orElseThrow(()->new RecordNotFoundException("User Not found"));
		User user = mapper.toUserEntity(dto);
		currentUser.setUsername(user.getUsername());
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
		return mapper.toUserDto(userRepo.findByEmail(email).orElseThrow(()->new RecordNotFoundException("User not Found")));
	}
	
	public void delete(Long id) {
		if(!userRepo.existsById(id))
			throw new RuntimeException("User Not Found");
		userRepo.deleteById(id);
	}
}
