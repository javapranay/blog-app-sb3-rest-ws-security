package com.smedia.blogsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smedia.blogsapp.payload.PostDto;
import com.smedia.blogsapp.payload.PostResponse;
import com.smedia.blogsapp.service.PostService;
import com.smedia.blogsapp.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
	private PostService postService;

	@Autowired
	public PostController(PostService postService) {

		this.postService = postService;
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postRequest) {

		return new ResponseEntity<PostDto>(postService.createPost(postRequest), HttpStatus.CREATED);
	}

	@GetMapping
	public PostResponse getAllPosts(
			@RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NO, required = false) int pageNo,
			@RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
	}

	@GetMapping("/{post_id}")
	public ResponseEntity<PostDto> getPostbyId(@PathVariable(name = "post_id") Long id) {
		return ResponseEntity.ok(postService.getPostbyId(id));
	}

	@PutMapping("/{post_id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "post_id") Long id) {
		PostDto postResponse = postService.updatePost(postDto, id);
		return new ResponseEntity<PostDto>(postResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{post_id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deletePost(@PathVariable(name = "post_id") Long id) {
		postService.deletePost(id);
		return new ResponseEntity<String>(String.format("Post with Id: %s is deleted successfully", id), HttpStatus.OK);
	}
}
