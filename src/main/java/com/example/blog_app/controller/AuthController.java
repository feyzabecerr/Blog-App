package com.example.blog_app.controller;

import com.example.blog_app.dto.LoginDto;
import com.example.blog_app.responses.LoginResponse;
import com.example.blog_app.dto.SignupDto;
import com.example.blog_app.model.User;
import com.example.blog_app.security.UserDetailsImpl;
import com.example.blog_app.security.jwt.JwtUtils;
import com.example.blog_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	PasswordEncoder encoder;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginDto loginDto) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

		System.out.println(token);

		Authentication authentication = authenticationManager.authenticate(token);

		System.out.println(authentication);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwtToken = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		return ResponseEntity.ok()
				.body(new LoginResponse(userDetails.getId(),
						userDetails.getUsername(),
						userDetails.getEmail(),
						jwtToken));

	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody SignupDto signupDto) {
		if (userService.findByUsername(signupDto.getUsername()).isPresent()) {
			return new ResponseEntity<>("Username already exists.", HttpStatus.BAD_REQUEST);
		}

		User user = new User();
		user.setUsername(signupDto.getUsername());
		user.setPassword(signupDto.getPassword());
		user.setEmail(signupDto.getEmail());

		userService.saveUser(user);

		return new ResponseEntity<>("User successfully registered.", HttpStatus.CREATED);
	}



}
