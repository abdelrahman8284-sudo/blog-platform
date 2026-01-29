package com.abdelrahman.blogplatorm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdelrahman.blogplatorm.entities.Category;
@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

	Optional<Category> findByName(String categoryName);
	boolean existsByName(String catName);

}
