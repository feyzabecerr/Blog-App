package com.example.blog_app.responses;

import com.example.blog_app.model.Like;
import lombok.Data;

@Data
public class LikeResponse {
	private final Long id;
	private final Long userId;
	private final Long postId;

	public LikeResponse(Like entity){
		this.id = entity.getId();
		this.userId = entity.getUser().getId();
		this.postId = entity.getPost().getId();
	}
}
