package com.abdelrahman.blogplatorm.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.abdelrahman.blogplatorm.dtos.requests.PostRequestDto;
import com.abdelrahman.blogplatorm.dtos.requests.TagRequestDto;
import com.abdelrahman.blogplatorm.dtos.responses.PostResponseDto;
import com.abdelrahman.blogplatorm.entities.Category;
import com.abdelrahman.blogplatorm.entities.Post;
import com.abdelrahman.blogplatorm.entities.Tag;
import com.abdelrahman.blogplatorm.entities.User;
import com.abdelrahman.blogplatorm.enums.Status;
import com.abdelrahman.blogplatorm.mappers.PostMapper;
import com.abdelrahman.blogplatorm.repositories.PostRepo;
import com.abdelrahman.blogplatorm.repositories.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepo postRepo;
	private final PostMapper mapper;
	private final TagService tagService;
	private final UserRepo userRepo;	
	private final CategoryService catService;

	public PostResponseDto insert(PostRequestDto requestDto) {
		List<String> tagsNames= requestDto.getTags();
		for (String tagName : tagsNames) {
			if(!tagService.isExist(tagName)) {
				tagService.insert(new TagRequestDto(tagName));
			}
		}
		Set<Tag> tags = new HashSet<>(tagService.getByNames(tagsNames));
		User author =userRepo.findById(requestDto.getUserId()).orElseThrow(()->new RuntimeException("User Not Found"));
		Category category = catService.getById(requestDto.getCategoryId());		
		Post post = mapper.toPost(requestDto);
		post.setCategory(category);
		post.setUser(author);
		post.setTags(tags);
		if(post.getStatus()==null) {
			post.setStatus(Status.DRAFT);
		}
		return mapper.toPostDto(postRepo.save(post));
	}

//	public Post update(Long id, Post post) {
//		Post currentPost = postRepo.findById(id).orElseThrow(()->new RuntimeException("Post Not Found"));
//		currentPost.setContent(post.getContent());
//		currentPost.setCategory(post.getCategory());
//		currentPost.setCreatedAt(post.getCreatedAt());
//		currentPost.setReadingTime(post.getReadingTime());
//		currentPost.setStatus(post.getSta);
//		return postRepo.save(post);
//	}

	public List<PostResponseDto> findAll() {
		
		return mapper.toListDto(postRepo.findAll());
	}

	public PostResponseDto findById(Long id) {
		return mapper.toPostDto(postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post Not Found")));	
	}
	
	public List<PostResponseDto> findByTitle(String title) {
		return 	mapper.toListDto(postRepo.findByTitle(title));
	}

	public String delete(Long id) {
		if(postRepo.findById(id).isEmpty()) {
			return "This post is not found to delete";
		}
		else {
			postRepo.deleteById(id);
			return "This post deleted successfully";
		}
	}

}