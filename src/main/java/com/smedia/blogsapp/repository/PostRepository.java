package com.smedia.blogsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smedia.blogsapp.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	
}
