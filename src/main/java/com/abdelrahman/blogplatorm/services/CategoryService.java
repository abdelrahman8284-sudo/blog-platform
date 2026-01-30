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

	public CategoryResponseDto update(Long id, CategoryRequestDto dto) {
	    Category tag = catRepo.findById(id).orElseThrow(() -> new RuntimeException("Tag Not found"));
	    tag.setName(dto.getName()); 
	    return mapper.toDto(catRepo.save(tag));
	}
	
	public CategoryResponseDto findByName(String categoryName) {
		return mapper.toDto(catRepo.findByName(categoryName).orElseThrow(()->new RuntimeException("Category Not found")));
	}
	
	public Category getByName(String catName) {
		return catRepo.findByName(catName).orElseThrow(()->new RuntimeException("Category not found"));
	}
	
	public List<CategoryResponseDto> findAll(){
		return mapper.toListDto(catRepo.findAll());
	}
	
	public List<CategoryResponseDto> listCategories(){
		List<CategoryResponseDto> dtos = mapper.toListDto(catRepo.findAllWithPostCount());	
		return dtos;
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
	
	public boolean isExistByName(String catName) {
		return catRepo.existsByName(catName);
	}
}
