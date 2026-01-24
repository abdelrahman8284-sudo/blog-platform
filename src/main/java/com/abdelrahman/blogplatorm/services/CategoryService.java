package com.abdelrahman.blogplatorm.services;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdelrahman.blogplatorm.entities.Category;
import com.abdelrahman.blogplatorm.repositories.CategoryRepo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;
	
	public Category insert(Category category) {
		return categoryRepo.save(category);
	}

	public Category update(Long id,Category category) {
		if(categoryRepo.findById(id).isEmpty()) {
			throw new RuntimeException("Category Not found");
		}
		return categoryRepo.save(category);
	}
	
	public Category findByName(String categoryName) {
		return categoryRepo.findByName(categoryName).orElseThrow(()->new RuntimeException("Category Not found"));
	}
	
	public List<Category> findAll(){
		return categoryRepo.findAll();
	}

	public Category findById(Long id) {	
		return categoryRepo.findById(id).orElseThrow(()->new RuntimeException("Category Not Found"));
	}
}
