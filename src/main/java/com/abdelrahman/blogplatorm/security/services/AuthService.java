package com.abdelrahman.blogplatorm.security.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.abdelrahman.blogplatorm.dtos.requests.UserRequestDto;
import com.abdelrahman.blogplatorm.dtos.responses.UserResponseDto;
import com.abdelrahman.blogplatorm.entities.User;
import com.abdelrahman.blogplatorm.enums.Role;
import com.abdelrahman.blogplatorm.exceptions.RecordNotFoundException;
import com.abdelrahman.blogplatorm.mappers.UserMapper;
import com.abdelrahman.blogplatorm.repositories.UserRepo;
import com.abdelrahman.blogplatorm.security.dtos.AuthResponse;
import com.abdelrahman.blogplatorm.security.dtos.MyUserPrinciple;
import com.abdelrahman.blogplatorm.security.dtos.UserLoginDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {
	private final AuthenticationManager manager;
	private final JwtService jwtService;
	private final UserRepo userRepo;
	private final PasswordEncoder encoder;
	private final UserMapper mapper;
	
	
	public UserResponseDto register(UserRequestDto dto) {
		User user = mapper.toUserEntity(dto);
		user.setPassword(encoder.encode(dto.getPassword()));
		// if i want to block create role admin by user 
		if(dto.getRole() == Role.ROLE_AUTHOR) {
		    user.setRole(Role.ROLE_AUTHOR);
		} else {
		    user.setRole(Role.ROLE_USER);
		}

		return mapper.toUserDto(userRepo.save(user));
	}
	
	private AuthResponse verfiy(UserLoginDto userDto) {
		Authentication authentication = manager.authenticate(
				new UsernamePasswordAuthenticationToken(
						userDto.getEmail(), userDto.getPassword()));
		if(authentication.isAuthenticated()) {
			var user = (MyUserPrinciple) authentication.getPrincipal();
			String authority = user.getAuthorities().iterator().next().getAuthority();
			String token = jwtService.generateToken(userDto.getEmail(), authority);
			UserResponseDto userDtoResponse = UserResponseDto.builder()
		               .id(user.getId())
		               .username(user.getUsernameValue())
		               .email(user.getUsername())
		               .role(Role.valueOf(authority))
		               .createdAt(user.getCreatedAt())
		               .build();
			
			return AuthResponse.builder()
	                .token(token)
	                .user(userDtoResponse)
	                .type("Bearer")
	                .build();
		}
		throw new BadCredentialsException("Invalid credentials");
	}
	
	public AuthResponse login(UserLoginDto loginDto) {
		
		return verfiy(loginDto);
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
		return "Password has been reset successfully!";
		
	}
}
