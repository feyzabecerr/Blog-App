package com.example.blog_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class DocumentDto {

	MultipartFile multipartFile;

	Long postId;
}
