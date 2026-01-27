package com.abdelrahman.blogplatorm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abdelrahman.blogplatorm.dtos.requests.CategoryRequestDto;
import com.abdelrahman.blogplatorm.dtos.responses.CategoryResponseDto;
import com.abdelrahman.blogplatorm.entities.Category;
import com.abdelrahman.blogplatorm.mappers.CategoryMapper;
import com.abdelrahman.blogplatorm.repositories.CategoryRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	
	private final CategoryRepo catRepo;
	
	private final CategoryMapper mapper;
	
	public CategoryResponseDto insert(CategoryRequestDto dto) {
		return mapper.toDto(catRepo.save(mapper.toEntity(dto)));
	}

	public CategoryResponseDto update(Long id,CategoryRequestDto dto) {
		if(catRepo.findById(id).isEmpty()) {
			throw new RuntimeException("Category Not found");
		}
		return mapper.toDto(catRepo.save(mapper.toEntity(dto)));
	}
	
	public CategoryResponseDto findByName(String categoryName) {
		return mapper.toDto(catRepo.findByName(categoryName).orElseThrow(()->new RuntimeException("Category Not found")));
	}
	
	public List<CategoryResponseDto> findAll(){
		return mapper.toListDto(catRepo.findAll());
	}

	public CategoryResponseDto findById(Long id) {	
		return mapper.toDto(catRepo.findById(id).orElseThrow(()->new RuntimeException("Category Not Found")));
	}
	
	public Category getById(Long id) {
	    return catRepo.findById(id)
	        .orElseThrow(() -> new RuntimeException("Category Not Found"));
	}

	public void delete(Long id) {
		if(!catRepo.existsById(id)) {
			throw new RuntimeException("Not Found !");
		}
		catRepo.deleteById(id);
	}
}
