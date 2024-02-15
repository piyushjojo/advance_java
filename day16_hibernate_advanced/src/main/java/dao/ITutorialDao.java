package dao;

import java.util.List;

import pojos.Tutorial;

public interface ITutorialDao {
	/*
	 * Add tut by author under existing topic User i/p : tut details , auth id ,
	 * topic id
	 */
	String addNewTutorial(Tutorial tutorial,long authorId,long topicId);
	//get only  tutorial details : only tut details
	List<Tutorial> getAllTutorials();
	//get complete tutorial details : including tut  , author , topic
	List<Tutorial> getCompleteDetails();
	//Search tutorials by topic name
	List<Tutorial> findByTopicName(String topicName);
}
