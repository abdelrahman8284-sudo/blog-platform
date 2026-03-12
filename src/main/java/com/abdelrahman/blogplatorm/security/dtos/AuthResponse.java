package com.abdelrahman.blogplatorm.security.dtos;

import com.abdelrahman.blogplatorm.dtos.responses.UserResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor@NoArgsConstructor@Setter@Getter@Builder
public class AuthResponse {

	private String token;
	
	private String type;
	
	private UserResponseDto user;
}
