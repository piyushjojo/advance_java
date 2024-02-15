package com.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.Author;
import com.bookstore.entity.AuthorId;

@Repository
public interface AuthorRepository extends JpaRepository<Author, AuthorId> {

	@Query("select a from Author a join fetch a.books where a.authorId = ?1")
	public Author fetchAuthorDetailsWithBooks(AuthorId id);

	@Query("select a from Author a where a.authorId.name = ?1") // or this will also : where a.name = ?1 : provided
																// there is no "name" property in Author pojo , it will
																// look for "name" property in AuthorId class
	public List<Author> fetchByName(String name);
}
