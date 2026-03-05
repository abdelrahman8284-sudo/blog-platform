package com.abdelrahman.blogplatorm.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.abdelrahman.blogplatorm.dtos.requests.PostRequestDto;
import com.abdelrahman.blogplatorm.dtos.responses.PostResponseDto;
import com.abdelrahman.blogplatorm.dtos.update.PostUpdateDto;
import com.abdelrahman.blogplatorm.entities.Category;
import com.abdelrahman.blogplatorm.entities.Post;
import com.abdelrahman.blogplatorm.entities.Tag;
import com.abdelrahman.blogplatorm.entities.User;
import com.abdelrahman.blogplatorm.enums.Status;
import com.abdelrahman.blogplatorm.exceptions.RecordNotFoundException;
import com.abdelrahman.blogplatorm.mappers.PostMapper;
import com.abdelrahman.blogplatorm.repositories.PostRepo;
import com.abdelrahman.blogplatorm.repositories.UserRepo;
import com.abdelrahman.blogplatorm.security.dtos.MyUserPrinciple;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepo postRepo;
	private final PostMapper mapper;
	private final TagService tagService;
	private final UserRepo userRepo;	
	private final CategoryService catService;

	@Transactional
	public PostResponseDto insert(PostRequestDto requestDto) {
		Set<Tag> tags = new HashSet<>(tagService.getOrCreateByNames(requestDto.getTags()));

		if(!isCurrentUser(requestDto.getUserId())) 
			throw new AccessDeniedException("userId Not Allowed");
		
	 	User author = userRepo.findById(requestDto.getUserId()).orElseThrow(()->new RecordNotFoundException("User Not Found"));
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

	public Page<PostResponseDto> findAll(int pageNumber,int pageSize,String sortBy ,String sortType) {
		
		Pageable page = PageRequest.of(pageNumber, pageSize, Direction.fromString(sortType),sortBy);
		Page<Post> posts = postRepo.findAllByStatus(Status.PUBLISHED,page);
		return posts.map(post->mapper.toPostDto(post));
	}
	
	public List<PostResponseDto> findDraftsForCurrentUser() {
	    MyUserPrinciple user = getCurrentUser();
	    
	    return mapper.toListDto(
	        postRepo.findByUserIdAndStatus(user.getId(), Status.DRAFT)
	    );
	}


	public PostResponseDto findById(Long id) {
		return mapper.toPostDto(postRepo.findByIdAndStatus(id,Status.PUBLISHED).orElseThrow(() -> new RecordNotFoundException("Post Not Found")));	
	}
	
	public List<PostResponseDto> findByTitle(String title) {
		return 	mapper.toListDto(postRepo.findByTitleContainingIgnoreCaseAndStatus(title,Status.PUBLISHED));
	}

	@Transactional
	public void delete(Long id) {
		if(!postRepo.existsById(id)) {
			throw new RecordNotFoundException("This post is not found!");
		}
		if( !isCurrentUser(postRepo.getReferenceById(id).getUser().getId()))
			throw  new AccessDeniedException("Not allowed");
		postRepo.deleteById(id);
	}
	
	@Transactional
	public PostResponseDto update(Long id,PostUpdateDto postUpdate) {
		
		Post post = postRepo.findById(id).orElseThrow(()->new RecordNotFoundException("Post Not Found"));
		if(!isCurrentUser(post.getUser().getId()))
		    throw new AccessDeniedException("You are not allowed to update this post");
		
		if(postUpdate.getTitle()!=null && !postUpdate.getTitle().isBlank()) {
			post.setTitle(postUpdate.getTitle());
		}
		if(postUpdate.getContent()!=null && !postUpdate.getContent().isBlank()) {
			post.setContent(postUpdate.getContent());
		}
		if(postUpdate.getCatName()!= null && !postUpdate.getCatName().isBlank()) {
			if(!catService.isExistByName(postUpdate.getCatName())){
				throw new RecordNotFoundException(postUpdate.getCatName() +"Not Found category");
			}
			Category category = catService.getByName(postUpdate.getCatName());
			post.setCategory(category);
		}
		
		if(postUpdate.getTagNames()!=null && !postUpdate.getTagNames().isEmpty()) {
			Set<Tag> tags = tagService.getOrCreateByNames(postUpdate.getTagNames()).stream().collect(Collectors.toSet());
			post.setTags(tags);
		}
		return mapper.toPostDto(postRepo.save(post));
	}
	@Transactional
	public PostResponseDto publishPost(Long id) {
		Post post = postRepo.findById(id).orElseThrow(()->new RecordNotFoundException("Not found post"));
		if(!isCurrentUser(post.getUser().getId()))
		    throw new AccessDeniedException("You are not allowed to update this post");
	
		if(post.getStatus()==Status.PUBLISHED) {
			throw new RuntimeException("Post already published");
		}
		post.setStatus(Status.PUBLISHED);
		post.setPublishedAt(LocalDateTime.now());
		return mapper.toPostDto(postRepo.save(post));
	}
	
	private MyUserPrinciple getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return (MyUserPrinciple) auth.getPrincipal();
	}
	
	public boolean isCurrentUser(Long userId) {
		
		MyUserPrinciple currentUser = getCurrentUser();
		return currentUser.getId().equals(userId);
	}

}