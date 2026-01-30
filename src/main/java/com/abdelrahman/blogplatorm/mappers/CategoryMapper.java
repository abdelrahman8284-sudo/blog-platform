package com.abdelrahman.blogplatorm.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.abdelrahman.blogplatorm.dtos.requests.CategoryRequestDto;
import com.abdelrahman.blogplatorm.dtos.responses.CategoryResponseDto;
import com.abdelrahman.blogplatorm.entities.Category;
import com.abdelrahman.blogplatorm.entities.Post;
import com.abdelrahman.blogplatorm.enums.Status;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	@Mapping(target="postCount",source="posts",qualifiedByName = "calculatePostCount")
	CategoryResponseDto toDto(Category cat);
	
	Category toEntity(CategoryRequestDto dto);
	
	List<CategoryResponseDto> toListDto(List<Category> categories);
	
	@Named("calculatePostCount")
	default long calculatePostCount(List<Post> posts) {
		if(posts==null) {
			return 0;
		}
		return posts.stream()
				.filter(post ->Status.PUBLISHED.equals(post.getStatus())).count();
	}
}
