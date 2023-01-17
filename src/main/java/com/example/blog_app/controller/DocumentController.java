package com.example.blog_app.controller;

import com.example.blog_app.repository.DocumentRepository;
import com.example.blog_app.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class DocumentController {
	@Autowired
	DocumentRepository imageRepository;

	@Autowired
	DocumentService documentService;

	/*
	@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.OPTIONS}, allowedHeaders = {"Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers"}, exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"})
	@PostMapping("/upload/image")
	public ResponseEntity<DocumentDto> uploadImage(@RequestParam("image") MultipartFile file)
			throws IOException {

		imageRepository.save(Document.builder()
				.docName(file.getOriginalFilename())
				.file(ImageUtility.compressImage(file.getBytes())).build());
		return ResponseEntity.status(HttpStatus.OK)
				.body(new DocumentDto("Image uploaded successfully: " +
						file.getOriginalFilename()));
	}
	*/

	@GetMapping("/image/{post_id}")
	public HttpEntity<byte[]> getDocument(@PathVariable Long post_id) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(documentService.getDocumentFileByPostId(post_id), httpHeaders, HttpStatus.OK);
	}

}
