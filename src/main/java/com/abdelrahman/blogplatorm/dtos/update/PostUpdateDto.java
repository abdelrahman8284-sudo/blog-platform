package com.abdelrahman.blogplatorm.dtos.update;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor@NoArgsConstructor@Getter@Setter
public class PostUpdateDto {

	private String title;
	
	private String content;
	
	private String catName;
	
	private List<String> tagNames;
}
