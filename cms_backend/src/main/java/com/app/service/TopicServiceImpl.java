package com.app.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.TopicRepository;
import com.app.dto.TopicDTO;
import com.app.entities.Topic;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {
	// dep : repo
	@Autowired
	private TopicRepository topicRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public TopicDTO addTopic(TopicDTO topic) {
		Topic transientTopic=mapper.map(topic, Topic.class);
		return mapper.map(topicRepo.save(transientTopic), TopicDTO.class);
	}

}
