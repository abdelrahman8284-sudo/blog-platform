package com.abdelrahman.blogplatorm.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor@NoArgsConstructor@Setter@Getter
public class CategoryResponseDto {

	private Long id;
	
	private String name;
	
	private Integer postCount;
}
