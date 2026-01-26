package com.abdelrahman.blogplatorm.dtos.requests;

import java.util.List;

import com.abdelrahman.blogplatorm.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor
public class PostRequestDto {
		
	private String title;
	
	private String content;
	
	private Status status;
	
	private Long userId;
	
 	private Long categoryId;
 	
 	private List<String> tags;
}
