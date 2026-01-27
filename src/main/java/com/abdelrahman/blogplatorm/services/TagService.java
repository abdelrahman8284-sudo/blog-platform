package com.abdelrahman.blogplatorm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abdelrahman.blogplatorm.dtos.requests.TagRequestDto;
import com.abdelrahman.blogplatorm.dtos.responses.TagResponseDto;
import com.abdelrahman.blogplatorm.entities.Tag;
import com.abdelrahman.blogplatorm.mappers.TagMapper;
import com.abdelrahman.blogplatorm.repositories.TagRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagService {

	private final TagRepo tagRepo;
	
	private final TagMapper mapper;
	
	public TagResponseDto insert(TagRequestDto dto) {
		return mapper.toDto(tagRepo.save(mapper.toEntity(dto)));
	}

	public TagResponseDto update(Long id,TagRequestDto dto) {
		if(tagRepo.findById(id).isEmpty()) {
			throw new RuntimeException("Tag Not found");
		}
		return mapper.toDto(tagRepo.save(mapper.toEntity(dto)));
	}
	
	public TagResponseDto findByName(String tagName) {
		return mapper.toDto(tagRepo.findByName(tagName).orElseThrow(()->new RuntimeException("Tag Not found")));
	}
	
	public List<TagResponseDto> findAll(){
		return mapper.toListDto(tagRepo.findAll());
	}

	public TagResponseDto findById(Long id) {	
		return mapper.toDto(tagRepo.findById(id).orElseThrow(()->new RuntimeException("Tag Not Found")));
	}

	public List<TagResponseDto> findAllById(List<Long> tagsIds) {
		return mapper.toListDto(tagRepo.findAllById(tagsIds));
	}
	
	public List<TagResponseDto> findAllByName(List<String> tagsNames){
		return mapper.toListDto(tagRepo.findAllByNameIn(tagsNames));
	}
	
	public List<Tag> getByNames(List<String> names) {
	    return tagRepo.findAllByNameIn(names);
	}
	
	public boolean isExist(String name) {
		return tagRepo.existsByName(name);
	}
}
