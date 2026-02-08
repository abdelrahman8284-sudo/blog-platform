package com.abdelrahman.blogplatorm.dtos.requests;

import java.util.List;

import com.abdelrahman.blogplatorm.enums.Status;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor
//@Schema(title="Post")
public class PostRequestDto {
	@NotBlank	
	private String title;
	@NotBlank
	private String content;
	@Schema(defaultValue = "DRAFT")
	private Status status=Status.DRAFT;
	
	@NotNull
	private Long userId;
	@NotNull
 	private Long categoryId;
 	@NotEmpty
 	@Schema(examples = {"python","computer","OOP"})
 	private List<String> tags;
}
