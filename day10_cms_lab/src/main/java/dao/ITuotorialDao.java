package dao;

import java.sql.SQLException;
import java.util.List;

import pojos.Tutorial;

public interface ITuotorialDao {
	List<String> getTutorialNamesByTopicId(int topicId) throws SQLException;
	//add a method to get complete tut details by its name
	Tutorial getTutorialDetailsByName(String tutName) throws SQLException;
	//add a method to increment visits for the chosen tut
	String updateVisits(String tutName) throws SQLException;
	//add a method to insert new tut
	String insertNewTutorial(Tutorial tutorial) throws SQLException;
}
