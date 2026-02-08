package com.abdelrahman.blogplatorm.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor@NoArgsConstructor@Setter@Getter
//@Schema(title = "Category")
public class CategoryRequestDto {
	@NotBlank
	@Schema(example = "Technology")
	private String name;
}
