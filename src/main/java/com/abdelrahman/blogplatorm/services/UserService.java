package com.abdelrahman.blogplatorm.services;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.abdelrahman.blogplatorm.dtos.requests.UserRequestDto;
import com.abdelrahman.blogplatorm.dtos.responses.UserResponseDto;
import com.abdelrahman.blogplatorm.entities.User;
import com.abdelrahman.blogplatorm.exceptions.RecordNotFoundException;
import com.abdelrahman.blogplatorm.mappers.UserMapper;
import com.abdelrahman.blogplatorm.repositories.UserRepo;
import com.abdelrahman.blogplatorm.security.dtos.MyUserPrinciple;
import com.abdelrahman.blogplatorm.security.dtos.UserLoginDto;
import com.abdelrahman.blogplatorm.security.services.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	
	private final UserRepo userRepo;
	private final UserMapper mapper;
	private final AuthenticationManager manager;
	private final JwtService jwtService;
	
	
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
		currentUser.setPassword(encoder.encode(user.getPassword()));
		return mapper.toUserDto(userRepo.save(currentUser));
	}
	
	public String resetPassword(Long id ,String newPassword) {
//		// للحصول على بيانات المستخدم اللي مسجل الدخول فعلا
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		MyUserPrinciple currentUser = (MyUserPrinciple) auth.getPrincipal();
//		if(!currentUser.getId().equals(id)) {
//			throw new RuntimeException("You are not allowed to modify another user's data.");
//		}
		User user = userRepo.findById(id).orElseThrow(()->new RecordNotFoundException("User Not Found"));
		user.setPassword(encoder.encode(newPassword));
		userRepo.save(user);
		return "Password has been reset successfully!";	}
	
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

	private String verfiy(UserLoginDto userDto) {
		Authentication authentication = manager.authenticate(
				new UsernamePasswordAuthenticationToken(
						userDto.getEmail(), userDto.getPassword()));
		if(authentication.isAuthenticated()) {
			var user = (MyUserPrinciple) authentication.getPrincipal();
			String authority = user.getAuthorities().iterator().next().getAuthority();
			return jwtService.generateToken(userDto.getEmail(),authority);
		}
		return "Fail";
	}
	
	public String login(UserLoginDto loginDto) {
		
		return verfiy(loginDto);
	}
}
