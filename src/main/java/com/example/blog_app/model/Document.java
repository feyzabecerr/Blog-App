package com.example.blog_app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String docName;

	private String type;

	@Column(length = 50000000)
	@Lob
//	@Type(type = "org.hibernate.type.ImageType")
	private byte[] file;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToOne
	@JoinColumn(name = "post_id", referencedColumnName = "id")
	private Post post;
	public Document(String docName, String type, byte[] file) {
		this.docName = docName;
		this.type = type;
		this.file = file;
	}

}
