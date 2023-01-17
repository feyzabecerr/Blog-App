package com.example.blog_app.dto;

import com.example.blog_app.model.Like;
import lombok.Data;

@Data
public class LikeDTO {
	Long id;

	Long userId;

	Long postId;

	public LikeDTO(Like entity) {
		this.id = entity.getId();
		this.userId = entity.getUser().getId();
		this.postId = entity.getPost().getId();
	}
}
