package com.example.blog_app.service;

import com.example.blog_app.dto.PostDTO;
import com.example.blog_app.model.Document;
import com.example.blog_app.model.Post;
import com.example.blog_app.model.User;
import com.example.blog_app.repository.DocumentRepository;
import com.example.blog_app.repository.PostRepository;
import com.example.blog_app.responses.LikeResponse;
import com.example.blog_app.responses.PostResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService {
	private final PostRepository postRepository;

	private final DocumentService documentService;

	private final LikeService likeService;

	private final UserService userService;

	public PostService(PostRepository postRepository, DocumentService documentService, UserService userService, @Lazy LikeService likeService){
		this.postRepository = postRepository;
		this.documentService = documentService;
		this.userService = userService;
		this.likeService = likeService;
	}

	public List<PostResponse> getAllPosts(Optional<Long> userId) {
		List<Post> list;
		if(userId.isPresent()) {
			list = postRepository.findByUserId(userId.get());
		}else
			list = postRepository.findAll();
		return list.stream().map(p -> {
			List<LikeResponse> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(p.getId()));
			return new PostResponse(p, likes);}).collect(Collectors.toList());
	}

	public Post getOnePost(Long taskId) {
		return postRepository.findById(taskId).
				orElseThrow(() -> new IllegalStateException("task with " + taskId + " doesn't exist"));
	}

	public Post updateOnePost(Long taskId, PostDTO postDTO) {
		Optional<Post> post = postRepository.findById(taskId);
		if(post.isPresent()){
			Post toUpdate = post.get();
			toUpdate.setDescription(postDTO.getDescription());
			toUpdate.setTitle(postDTO.getTitle());
			postRepository.save(toUpdate);

			return toUpdate;
		}
		else{
			throw new IllegalStateException();
		}
	}

	public void createOnePost(PostDTO postDTO) {
		User user = userService.getOneUser(postDTO.getUserId());
		if(user == null)
			return;
		Document document = documentService.getOneDocument(postDTO.getDocId());
		Post post = new Post();
		post.setDescription(postDTO.getDescription());
		post.setTitle(postDTO.getTitle());
		post.setDate(postDTO.getDate());
		post.setUser(user);
		post.setDocument(document);
		document.setPost(post);
		postRepository.save(post);
	}

	public void deleteById(Long id) {
		if (postRepository.existsById(id)) {
			System.out.println("deleted with " + id );
			documentService.deleteImage(id);
			postRepository.deleteById(id);
		}
	}

}
