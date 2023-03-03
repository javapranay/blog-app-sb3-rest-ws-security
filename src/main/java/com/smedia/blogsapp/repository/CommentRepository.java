package com.smedia.blogsapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smedia.blogsapp.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	List<Comment> findByPostId(Long postId);
}
