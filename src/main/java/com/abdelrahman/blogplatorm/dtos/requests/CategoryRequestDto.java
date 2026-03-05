package com.abdelrahman.blogplatorm.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor@NoArgsConstructor@Setter@Getter
//@Schema(title = "Category")
public class CategoryRequestDto {
	@NotBlank(message = "Category name required")
	@Size(min = 2,max=50,message = "Category name must be between {min} and {max} characters")
	@Pattern(regexp= "^[\\w\\s-]+$", message= "Category name can only contain Letters, numbers, spaces,and hyphens")
	@Schema(example = "Technology")
	private String name;
}
