package com.example.blog_app.repository;

import com.example.blog_app.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
	List<Post> findByUserId(Long aLong);

}
