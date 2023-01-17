package com.example.blog_app.service;

import com.example.blog_app.dto.LikeDTO;
import com.example.blog_app.model.Like;
import com.example.blog_app.model.LikePost;
import com.example.blog_app.model.Post;
import com.example.blog_app.model.User;
import com.example.blog_app.repository.LikeRepository;
import com.example.blog_app.responses.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {

	private final LikeRepository likeRepository;
	private final UserService userService;
	private final PostService postService;

	public LikeService(LikeRepository likeRepository, UserService userService,
					   PostService postService) {
		this.likeRepository = likeRepository;
		this.userService = userService;
		this.postService = postService;
	}


	public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
		List<Like> list;
		if(userId.isPresent() && postId.isPresent()) {
			list = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
		}else if(userId.isPresent()) {
			list = likeRepository.findByUserId(userId.get());
		}else if(postId.isPresent()) {
			list = likeRepository.findByPostId(postId.get());
		}else
			list = likeRepository.findAll();
		return list.stream().map(LikeResponse::new).collect(Collectors.toList());
	}

	public Like getOneLikeById(Long LikeId) {
		return likeRepository.findById(LikeId).orElse(null);
	}

	public Like createOneLike(LikePost likePost) {
		User user = userService.getUserById(likePost.getUserId());
		Post post = postService.getOnePost(likePost.getPostId());
		if(user != null && post != null) {
			Like likeToSave = new Like();
			likeToSave.setId(likePost.getId());
			likeToSave.setPost(post);
			likeToSave.setUser(user);
			return likeRepository.save(likeToSave);
		}else
			return null;
	}

	public void deleteOneLikeById(Long id) {
		if(likeRepository.existsById(id)) {
			likeRepository.deleteById(id);
		}
	}

}
