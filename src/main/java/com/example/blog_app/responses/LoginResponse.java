package com.example.blog_app.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

	private Long id;

	private String username;

	private String email;

	private String jwtToken;
}