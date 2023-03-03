package com.smedia.blogsapp.service;

import java.util.List;

import com.smedia.blogsapp.entity.Post;
import com.smedia.blogsapp.payload.PostDto;
import com.smedia.blogsapp.payload.PostResponse;

public interface PostService {
	PostDto createPost(PostDto postDto);
	
	PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
	
	PostDto getPostbyId(Long id);
	
	PostDto updatePost(PostDto postDto, Long id);
	
	void deletePost(Long id);
}
