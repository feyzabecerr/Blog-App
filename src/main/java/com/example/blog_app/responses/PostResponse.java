package com.example.blog_app.responses;

import com.example.blog_app.model.Like;
import com.example.blog_app.model.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {

	private final Long id;

	private final Long userId;

	private final String userName;

	private final String title;

	private final String description;

	List<LikeResponse> postLikes;
	public PostResponse(Post entity, List<LikeResponse> likes){
		this.id = entity.getId();
		this.userId = entity.getUser().getId();
		this.userName = entity.getUser().getUsername();
		this.title = entity.getTitle();
		this.description = entity.getDescription();
		this.postLikes = likes;
	}
}
