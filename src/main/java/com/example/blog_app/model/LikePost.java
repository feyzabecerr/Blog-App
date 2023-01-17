package com.example.blog_app.model;

import lombok.Data;

@Data
public class LikePost {
	Long id;

	Long userId;

	Long postId;
}
