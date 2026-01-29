package com.abdelrahman.blogplatorm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abdelrahman.blogplatorm.dtos.requests.TagRequestDto;
import com.abdelrahman.blogplatorm.dtos.responses.TagResponseDto;
import com.abdelrahman.blogplatorm.entities.Tag;
import com.abdelrahman.blogplatorm.mappers.TagMapper;
import com.abdelrahman.blogplatorm.repositories.TagRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagService {

	private final TagRepo tagRepo;
	
	private final TagMapper mapper;
	
	public TagResponseDto insert(TagRequestDto dto) {
		return mapper.toDto(tagRepo.save(mapper.toEntity(dto)));
	}

	public TagResponseDto update(Long id, TagRequestDto dto) {
	    Tag tag = tagRepo.findById(id).orElseThrow(() -> new RuntimeException("Tag Not found"));
	    tag.setName(dto.getName());
	    return mapper.toDto(tagRepo.save(tag));
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
	@Transactional
	public List<Tag> getOrCreateByNames(List<String> names) {
		List<String> normNames = names.stream().map(String::trim).map(String::toLowerCase).toList();
		for (String name : normNames) {
			if(!isExist(name)) {
				insert(new TagRequestDto(name));
			}
		}
		
	    return tagRepo.findAllByNameIn(normNames);
	}
	
	public boolean isExist(String name) {
		return tagRepo.existsByName(name);
	}
}
