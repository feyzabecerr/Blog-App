package com.example.blog_app.dto;

import com.example.blog_app.model.Document;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class PostDTO {

	String description;

	String title;

	Long userId;

	LocalDate date;

	Long docId;

}
