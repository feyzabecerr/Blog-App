package com.example.blog_app.service;

import com.example.blog_app.model.ERole;
import com.example.blog_app.model.Role;
import com.example.blog_app.model.User;
import com.example.blog_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}


	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Role userRole = new Role(null, ERole.ROLE_USER);
		user.setRoles(new HashSet<>(List.of(userRole)));
		return userRepository.save(user);
	}

	public User getOneUser(Long id) {
		return userRepository.findById(id).
				orElseThrow(() -> new IllegalStateException("user with " + id + " doesn't exist"));
	}

	public User updateOneUser(Long id, User newUser) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			User foundUser = user.get();
			foundUser.setUsername(newUser.getUsername());
			foundUser.setEmail(newUser.getEmail());
			foundUser.setPassword(newUser.getPassword());
			userRepository.save(foundUser);
			return foundUser;
		} else {
			throw new IllegalStateException();
		}
	}

	public User getUserById(long id) {
		User user = userRepository.findById(id).orElseThrow(IllegalStateException::new);
		return user;
	}

	public void deleteById(long id){
		if (userRepository.existsById(id)){
			userRepository.deleteById(id);
		}
	}

	public Optional<User> findByUsername(String username){
		return userRepository.findByUsername(username);
	}

}
