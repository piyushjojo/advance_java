package com.app.dao;

import java.util.List;

import com.app.pojos.Tutorial;

public interface ITutorialDao {
	List<String> getTutorialNamesByTopic(long topicId);

	Tutorial getTutorialDetails(String tutName);
}
