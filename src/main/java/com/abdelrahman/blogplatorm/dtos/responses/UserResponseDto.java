package com.abdelrahman.blogplatorm.dtos.responses;

import java.time.LocalDateTime;
import java.util.List;

import com.abdelrahman.blogplatorm.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor
public class UserResponseDto {

	private Long id;
	
	private String name;
	
	private String email;
	
	private Role role;
	
	private LocalDateTime createdAt;
	
}
