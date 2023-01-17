package com.example.blog_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	@NotBlank(message = "Description is mandatory")
	private String title;

	@NotBlank(message = "Description is mandatory")
	@Column(columnDefinition = "text")
	private String description;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	User user;


	@OneToOne(mappedBy = "post")
	@JsonIgnore
	private Document document;
}
