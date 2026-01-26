package com.abdelrahman.blogplatorm.mappers;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.abdelrahman.blogplatorm.dtos.requests.PostRequestDto;
import com.abdelrahman.blogplatorm.dtos.responses.PostResponseDto;
import com.abdelrahman.blogplatorm.entities.Post;
import com.abdelrahman.blogplatorm.entities.Tag;

@Mapper(componentModel = "spring")
public interface PostMapper {
	

	@Mappings({
		@Mapping(target="userName",source="user.name"),
		@Mapping(target="categoryName",source="category.name")
	})
	PostResponseDto toPostDto(Post post);
	@Mappings({
			@Mapping(target="user",ignore=true),
			@Mapping(target="category",ignore=true),
			@Mapping(target="tags",ignore=true)
	})
	Post toPost(PostRequestDto dto);// mapping in service layer the ids
	
	List<PostResponseDto> toListDto(List<Post> posts);
	
//	default List<Tag> mapToTags(@MappingTarget Post post, PostRequestDto dto){
//		
//		List<Tag> tags = new ArrayList<>();
//	}
	
	@AfterMapping
	default void mapTagsNames(Post post , @MappingTarget PostResponseDto dto) {
		if(post.getTags()!=null) {
			dto.setTagsNames(post.getTags().stream().map(Tag::getName).toList());
		}
	}
}
