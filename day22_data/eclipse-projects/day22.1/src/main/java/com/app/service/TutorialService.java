package com.app.service;

import java.util.List;
import java.util.Map;

import com.app.dto.TutorialDTO;
import com.app.dto.TutorialRespDTO;

public interface TutorialService {
	TutorialRespDTO addNewTutorial(long topicId, TutorialDTO tutDTO);

	List<TutorialDTO> getTutDetailsByTopic(long topicId);

	TutorialDTO partialUpdateTutorial(long topicId, long tutId, Map<String, Object> fields);

	TutorialDTO getTutorial(long topicId, long tutId);
}
