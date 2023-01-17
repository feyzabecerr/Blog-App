package com.example.blog_app.repository;

import com.example.blog_app.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface DocumentRepository extends JpaRepository<Document,Long> {

	@Query(value = "SELECT * from document d where d.post_id = :post_id", nativeQuery = true)
	Optional<Document> findDocumentByPostId(Long post_id);


	@Query(value = "DELETE from document d where d.post_id = :post_id", nativeQuery = true)
	@Modifying(clearAutomatically = true)
	public void deleteDocumentByPostId(Long post_id);
}
