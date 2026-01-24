package com.abdelrahman.blogplatorm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdelrahman.blogplatorm.entities.Tag;
@Repository
public interface TagRepo extends JpaRepository<Tag, Long> {

	Optional<Tag> findByName(String name);
}
