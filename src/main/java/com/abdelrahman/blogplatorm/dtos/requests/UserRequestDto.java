package com.abdelrahman.blogplatorm.dtos.requests;

import com.abdelrahman.blogplatorm.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor
public class UserRequestDto {

	
	private String username;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String password;
	
	private Role role;
	
}
