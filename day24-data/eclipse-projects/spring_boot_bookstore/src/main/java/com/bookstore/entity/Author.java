package com.bookstore.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "authors")
@Getter
@Setter
@ToString(exclude = "books")
@NoArgsConstructor

public class Author {
	@EmbeddedId // or @Id also works !
	private AuthorId authorId;
	@Column(length = 30)
	private String genre;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "author" , orphanRemoval = true )
	private List<Book> books = new ArrayList<>();

	public Author(AuthorId authorId, String genre) {
		super();
		this.authorId = authorId;
		this.genre = genre;
	}

	public void addBook(Book book) {
		this.books.add(book);//establishing a link Author ---> Book
		book.setAuthor(this);//establishing rev link Book ---> Author
	}

	public void removeBook(Book book) {
		this.books.remove(book);//delinking : Author ---X--- Book
		book.setAuthor(null);//delinking : Book ---X--- Author
		
	}

	public void removeBooks() {
		Iterator<Book> iterator = this.books.iterator();

		while (iterator.hasNext()) {
			Book book = iterator.next();

			book.setAuthor(null);
			iterator.remove();
		}
	}

}
