package com.example.blog_app.controller;

import com.example.blog_app.model.Post;
import com.example.blog_app.model.User;
import com.example.blog_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController
{
	@Autowired
	private UserService userService;

	@GetMapping
	public List<User> getUsers()
	{
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	User getOneUser(@PathVariable Long id){
		return userService.getOneUser(id);
	}
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
		userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	@PutMapping("/{id}")
	public User updateOneUser(@PathVariable Long id, @RequestBody User newUser){
		return userService.updateOneUser(id,newUser);
	}

	@PostMapping("/{id}/posts")
	public ResponseEntity<User> addPostToUser(@Valid @PathVariable long id, @RequestBody Post post){
		User user = userService.getUserById(id);
		user.getPostList().add(post);
		return ResponseEntity.status(HttpStatus.OK).body(userService.saveUser(user));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		userService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}