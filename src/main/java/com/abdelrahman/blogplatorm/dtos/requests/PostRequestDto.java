package com.abdelrahman.blogplatorm.dtos.requests;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import com.abdelrahman.blogplatorm.enums.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor
public class PostRequestDto {
	@NotBlank	
	private String title;
	@NotBlank
	private String content;
	
	private Status status=Status.DRAFT;
	
	@NotNull
	private Long userId;
	@NotNull
 	private Long categoryId;
 	@NotEmpty
 	private List<String> tags;
}
