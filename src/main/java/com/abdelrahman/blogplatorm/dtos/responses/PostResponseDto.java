package com.abdelrahman.blogplatorm.dtos.responses;

import java.time.LocalDateTime;
import java.util.List;

import com.abdelrahman.blogplatorm.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter@Getter@AllArgsConstructor@NoArgsConstructor
public class PostResponseDto {

	private Long id;
	
	private String title;
	
	private String content;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private Status status;
	
	private Integer readingTime;
	
	private String userName;
	
 	private String categoryName;
 	
 	private List<String> tagsNames;
}
