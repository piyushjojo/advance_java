package com.app.service;

import java.util.List;

import com.app.pojos.Tutorial;

public interface ITutorialService {
	List<String> getTutorialNamesByTopic(long topicId);

	Tutorial getTutorialDetails(String tutName);
}
