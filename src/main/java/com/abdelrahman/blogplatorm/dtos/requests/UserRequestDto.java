package com.abdelrahman.blogplatorm.dtos.requests;

import com.abdelrahman.blogplatorm.enums.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor
//@Schema(title="User")
public class UserRequestDto {

	@NotBlank
	private String username;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String password;
	
	private Role role;
	
}
