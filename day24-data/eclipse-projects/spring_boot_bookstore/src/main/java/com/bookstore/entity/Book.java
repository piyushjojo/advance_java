package com.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "books")
@Getter
@Setter
@ToString(exclude = "author")
@NoArgsConstructor
public class Book

{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 100)
	private String title;
	@Column(length = 100)
	private String isbn;
	@ManyToOne(fetch = FetchType.LAZY)
	//Optional BUT reco annotation to specify comp. FKs
	@JoinColumns({ @JoinColumn(name = "name",referencedColumnName = "name"),
			@JoinColumn(name = "age",referencedColumnName = "age") })
	private Author author;
	public Book(String title, String isbn) {
		super();
		this.title = title;
		this.isbn = isbn;
	}
	
}
