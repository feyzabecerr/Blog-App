package com.example.blog_app.controller;


import com.example.blog_app.dto.PostDTO;
import com.example.blog_app.model.Document;
import com.example.blog_app.model.Post;
import com.example.blog_app.responses.PostResponse;
import com.example.blog_app.service.DocumentService;
import com.example.blog_app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/posts")
public class PostController {
	@Autowired
	private final PostService postService;

	@Autowired
	private final DocumentService documentService;

	public PostController(PostService postService, DocumentService documentService) {
		this.postService = postService;
		this.documentService = documentService;
	}


	@GetMapping
	public List<PostResponse> getPosts(@RequestParam Optional<Long> userId) {
		return postService.getAllPosts(userId);
	}

	@GetMapping("/{id}")
	public Post getPost(@PathVariable Long id) {
		return postService.getOnePost(id);
	}


	@PostMapping(value = "/api", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	ResponseEntity<PostDTO> createPost(@Valid @RequestPart("post") PostDTO postDTO, @RequestPart("image") MultipartFile file) {
		//	postService.createOnePost(postDTO);
		try{
			Document image = documentService.saveImage(file);
			postDTO.setDocId(image.getId());
			postService.createOnePost(postDTO);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(postDTO);
	}

	@PutMapping("/{id}")
	public Post updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
		return postService.updateOnePost(id, postDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePost(@PathVariable Long id) {
		postService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
