package com.smedia.blogsapp.payload;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PostDto {
	private Long id;
	
	// title should not be null  or empty
    // title should have at least 2 characters
	@NotEmpty
	@Size(min = 2, message = "Post title should have at least 2 characters")
	private String title;
	
	// post description should be not null or empty
    // post description should have at least 10 characters
	@NotEmpty
	@Size(min = 10, message = "Post description should have at least 10 characters")
	private String description;
	
	 // post content should not be null or empty
	@NotEmpty
	private String content;
	private Set<CommentDto> comments;
	
	public PostDto() {
		super();
		
	}

	public PostDto(Long id, String title, String description, String content) {
		
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
	}

	public PostDto(Long id, String title, String description, String content, Set<CommentDto> comments) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
		this.comments = comments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Set<CommentDto> getComments() {
		return comments;
	}

	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return String.format("PostDto [id=%s, title=%s, description=%s, content=%s, comments=%s]", id, title,
				description, content, comments);
	}

	
}
