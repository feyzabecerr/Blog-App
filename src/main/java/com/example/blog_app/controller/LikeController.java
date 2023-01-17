package com.example.blog_app.controller;

import com.example.blog_app.dto.LikeDTO;
import com.example.blog_app.model.Like;
import com.example.blog_app.model.LikePost;
import com.example.blog_app.responses.LikeResponse;
import com.example.blog_app.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

	@Autowired
	private LikeService likeService;

	public LikeController(LikeService likeService) {
		this.likeService = likeService;
	}

	@GetMapping
	public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId,
										  @RequestParam Optional<Long> postId) {
		return likeService.getAllLikesWithParam(userId, postId);
	}

	@PostMapping
	public Like createOneLike(@RequestBody LikePost likePost) {
		return likeService.createOneLike(likePost);
	}

	@GetMapping("/{likeId}")
	public Like getOneLike(@PathVariable Long likeId) {
		return likeService.getOneLikeById(likeId);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLike(@PathVariable Long id) {
		likeService.deleteOneLikeById(id);
		return ResponseEntity.noContent().build();
	}

}
