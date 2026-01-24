package com.abdelrahman.blogplatorm.services;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdelrahman.blogplatorm.entities.Tag;
import com.abdelrahman.blogplatorm.repositories.TagRepo;

@Service
public class TagService {

	@Autowired
	private TagRepo tagRepo;
	
	public Tag insert(Tag tag) {
		return tagRepo.save(tag);
	}

	public Tag update(Long id,Tag tag) {
		if(tagRepo.findById(id).isEmpty()) {
			throw new RuntimeException("Tag Not found");
		}
		return tagRepo.save(tag);
	}
	
	public Tag findByName(String tagName) {
		return tagRepo.findByName(tagName).orElseThrow(()->new RuntimeException("Tag Not found"));
	}
	
	public List<Tag> findAll(){
		return tagRepo.findAll();
	}

	public Tag findById(Long id) {	
		return tagRepo.findById(id).orElseThrow(()->new RuntimeException("Tag Not Found"));
	}
}
