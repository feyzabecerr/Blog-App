package com.example.blog_app.service;

import com.example.blog_app.model.Document;
import com.example.blog_app.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class DocumentService {

	@Autowired
	private DocumentRepository documentRepository;

	public byte[] getDocumentFileByPostId(Long id) {
		return documentRepository.findDocumentByPostId(id).get().getFile();
	}

	public Document getOneDocument(Long id) {
		return documentRepository.findById(id).
				orElseThrow(() -> new IllegalStateException("document with " + id + " doesn't exist"));
	}

	public Set<Document> findAllDocuments(){
		return (Set<Document>) documentRepository.findAll();
	}
	public Document saveImage(MultipartFile multipartFile) throws IOException{

		Document document = new Document(
				multipartFile.getOriginalFilename(),
				multipartFile.getContentType(),
				multipartFile.getBytes()
		);

		return documentRepository.save(document);
	}

	public void deleteImage(Long id){
		documentRepository.deleteDocumentByPostId(id);

	}

}
