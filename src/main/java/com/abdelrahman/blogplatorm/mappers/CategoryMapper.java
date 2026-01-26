package com.abdelrahman.blogplatorm.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.abdelrahman.blogplatorm.dtos.requests.CategoryRequestDto;
import com.abdelrahman.blogplatorm.dtos.responses.CategoryResponseDto;
import com.abdelrahman.blogplatorm.entities.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	@Mapping(target="postCount",expression = "java(cat.getPosts() != null?cat.getPosts().size():0)")
	CategoryResponseDto toDto(Category cat);
	
	Category toEntity(CategoryRequestDto dto);
	
	List<CategoryResponseDto> toListDto(List<Category> categories);
}
