package com.bookstore.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bookstore.entity.Author;
import com.bookstore.entity.AuthorId;
import com.bookstore.entity.Book;

@SpringBootTest
class TestBookStoreService {
	@Autowired
	private BookstoreService service;

	@Test
	void testAddAuthorWithBooks() {
		Author author = new Author(new AuthorId("Rama Pathak",45),"Fiction");
		Book book1 = new Book("Catch 22","fiction-001");
		Book book2 = new Book("Trust","fiction-002");
		Book book3=new Book("The Maid", "fiction-003");
		author.addBook(book1); // use addBook() helper
		author.addBook(book2);
		author.addBook(book3);
		Author persistentAuthor = service.addAuthorWithBooks(author);
		System.out.println(persistentAuthor);
	}
	@Test
	void testFetchAuthorByName()
	{
		List<Author> authors = service.fetchAuthorByName("Rama Pathak");
		authors.forEach(System.out::println);
	//	authors.forEach(a -> a.getBooks().forEach(System.out::println));un comment to understand lazy init exc
	}

}
