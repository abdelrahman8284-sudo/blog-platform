package com.abdelrahman.blogplatorm.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor@NoArgsConstructor@Setter@Getter
public class TagRequestDto {
	@NotBlank
	private String name;
}
