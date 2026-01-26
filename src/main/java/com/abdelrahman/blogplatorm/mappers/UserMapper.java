package com.abdelrahman.blogplatorm.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.abdelrahman.blogplatorm.dtos.requests.UserRequestDto;
import com.abdelrahman.blogplatorm.dtos.responses.UserResponseDto;
import com.abdelrahman.blogplatorm.entities.User;

@Mapper(componentModel = "spring",uses = PostMapper.class)
public interface UserMapper {

	UserResponseDto toUserDto(User user);
	
	User toUserEntity(UserRequestDto dto);
	
	List<UserResponseDto> toList(List<User> users);
}
