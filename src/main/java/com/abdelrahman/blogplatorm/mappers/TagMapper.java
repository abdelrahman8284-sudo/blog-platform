package com.abdelrahman.blogplatorm.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.abdelrahman.blogplatorm.dtos.requests.TagRequestDto;
import com.abdelrahman.blogplatorm.dtos.responses.TagResponseDto;
import com.abdelrahman.blogplatorm.entities.Tag;

@Mapper(componentModel = "spring")
public interface TagMapper {

	@Mapping(target="postCount",expression = "java(tag.getPosts()!=null?tag.getPosts().size():0)")
	TagResponseDto toDto(Tag tag);
	
	Tag toEntity(TagRequestDto dto); 
	
	List<TagResponseDto> toListDto(List<Tag> tags);
}
