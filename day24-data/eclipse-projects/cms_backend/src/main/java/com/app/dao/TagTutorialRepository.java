package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.TagTutorial;

public interface TagTutorialRepository extends JpaRepository<TagTutorial, Long> {
	
	long deleteByTutorialAuthorId(long id);
}
