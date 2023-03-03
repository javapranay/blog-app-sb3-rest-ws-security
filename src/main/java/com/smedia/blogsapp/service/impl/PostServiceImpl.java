package com.smedia.blogsapp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.smedia.blogsapp.entity.Post;
import com.smedia.blogsapp.exception.ResourceNotFoundException;
import com.smedia.blogsapp.payload.PostDto;
import com.smedia.blogsapp.payload.PostResponse;
import com.smedia.blogsapp.repository.PostRepository;
import com.smedia.blogsapp.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;
	private ModelMapper mapper;

	public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
		super();
		this.postRepository = postRepository;
		this.mapper = mapper;
	}

	@Override
	public PostDto createPost(PostDto postDto) {
		// Convert DTO to entity
		Post newPost = mapToEntity(postDto);

		Post savedPost = postRepository.save(newPost);

		// Convert entity to DTO
		PostDto postResp = mapToDto(savedPost);

		return postResp;
	}

	// Retrieve all posts with pagination and sorting
	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		// Sorting
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		// Create Pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Post> pageContent = postRepository.findAll(pageable);

		// Get content from page object
		List<Post> listOfPosts = pageContent.getContent();
		List<PostDto> postDtos = listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNo(pageNo);
		postResponse.setPageSize(pageSize);
		postResponse.setTotalElements(pageContent.getTotalElements());
		postResponse.setTotalPages(pageContent.getTotalPages());
		postResponse.setLast(pageContent.isLast());
		postResponse.setFirst(pageContent.isFirst());
		return postResponse;
	}

	@Override
	public PostDto getPostbyId(Long id) {
		Optional<Post> postOptional = postRepository.findById(id);
		Post post = postOptional.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id.toString()));
		PostDto postDto = mapToDto(post);
		return postDto;
	}

	@Override
	public PostDto updatePost(PostDto postDto, Long id) {
		// first get the post from db by id
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id.toString()));

		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());

		Post updatedPost = postRepository.save(post);

		return mapToDto(updatedPost);
	}

	@Override
	public void deletePost(Long id) {
		// get the post by id from database
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id.toString()));

		postRepository.delete(post);
	}

	// Mapping dto to entity
	private Post mapToEntity(PostDto postDto) {
//		Post newPost = new Post();
//		newPost.setTitle(postDto.getTitle());
//		newPost.setDescription(postDto.getDescription());
//		newPost.setContent(postDto.getContent());
//		newPost.setComments(postDto.getComments().stream()
//				.map(commentDto -> new CommentServiceImpl().mapToEntity(commentDto)).collect(Collectors.toSet()));
		
		Post newPost = mapper.map(postDto, Post.class);
		return newPost;
	}

	// Mapping entity to dto
	private PostDto mapToDto(Post savedPost) {
//		PostDto postResp = new PostDto();
//		postResp.setId(savedPost.getId());
//		postResp.setTitle(savedPost.getTitle());
//		postResp.setDescription(savedPost.getDescription());
//		postResp.setContent(savedPost.getContent());
//		postResp.setComments(savedPost.getComments().stream().map(comment -> new CommentServiceImpl().mapToDTO(comment))
//				.collect(Collectors.toSet()));
		
		PostDto postResp = mapper.map(savedPost, PostDto.class);
		return postResp;
	}

}
