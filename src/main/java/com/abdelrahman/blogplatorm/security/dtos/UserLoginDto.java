package com.abdelrahman.blogplatorm.security.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter@AllArgsConstructor
public class UserLoginDto {

	private String email;
	
	private String password;
}
