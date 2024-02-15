package com.app.service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.TopicRepository;
import com.app.dao.TutorialRepository;
import com.app.dao.UserRepository;
import com.app.dto.TutorialDTO;
import com.app.dto.TutorialRespDTO;
import com.app.entities.Topic;
import com.app.entities.Tutorial;
import com.app.entities.User;

@Service
@Transactional
public class TutorialServiceImpl implements TutorialService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private TopicRepository topicRepo;

	@Autowired
	private TutorialRepository tutRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public TutorialDTO addNewTutorial(TutorialDTO tutDTO) {
		// get topic from topic id
		Topic topic = topicRepo.findById(tutDTO.getTopicId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid topic id!!!"));
		// get author from author id
		User author = userRepo.findById(tutDTO.getAuthorId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid author id!!!"));
		// NO need to verify user's role as author : since this authorization will be
		// taken care by spring security
		// map tut dto --> tut entity
		Tutorial transientTut = mapper.map(tutDTO, Tutorial.class);
		// establish many --> one relationship (Tut *-->1 Topic)
		transientTut.setTopic(topic);
		// establish many --> one relationship (Tut *-->1 User : author)
		transientTut.setAuthor(author);
		// save tut , map persistent tut --> tut resp dto
		;
		tutDTO.setId(tutRepo.save(transientTut).getId());
		//un comment this to send a better tut resp (do that after checking lazy vs eager)
	//	TutorialRespDTO resp = mapper.map(tutRepo.save(transientTut), TutorialRespDTO.class);
	//	resp.setAuthorName(author.getFirstName()+" "+author.getLastName());
	//	System.out.println(resp);
		return tutDTO;
	}

	@Override
	public List<TutorialDTO> getTutDetailsByTopic(long topicId) {
		return tutRepo.findByTopicId(topicId).stream().map(tut -> mapper.map(tut, TutorialDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public TutorialDTO partialUpdateTutorial(long topicId, long tutId, Map<String, Object> fields) {
		// validate topic id
		Topic topic = topicRepo.findById(topicId).orElseThrow(() -> new ResourceNotFoundException("Invalid Topic ID"));
		// => topic found : actually tut can be found directly using tut id -- but since
		// it's a sub resource , first verifying resource(topic) n then verifying sub
		// resource
		Tutorial tutorial = tutRepo.findById(tutId).orElseThrow(() -> new ResourceNotFoundException("Invalid Tut  ID"));
		fields.forEach((k, v) -> {
			// reflection API
			Field field = ReflectionUtils.findField(Tutorial.class, k);
			field.setAccessible(true);
			// System.out.println(field.getType());
//			if (field.getType()==LocalDate.class)
			if (k.equals("publishDate"))
				ReflectionUtils.setField(field, tutorial, LocalDate.parse(v.toString()));
			else
				ReflectionUtils.setField(field, tutorial, v);
		});
		System.out.println("updated tut " + tutorial);
		return mapper.map(tutRepo.save(tutorial), TutorialDTO.class);

	}

	@Override
	public TutorialDTO getTutorial(long topicId, long tutId) {
		// validate topic id
		Topic topic = topicRepo.findById(topicId).orElseThrow(() -> new ResourceNotFoundException("Invalid Topic ID"));
		// => topic found : actually tut can be found directly using tut id -- but since
		// it's a sub resource , first verifying resource(topic) n then verifying sub
		// resource
		Tutorial tutorial = tutRepo.findById(tutId).orElseThrow(() -> new ResourceNotFoundException("Invalid Tut  ID"));
		return mapper.map(tutorial, TutorialDTO.class);
	}

}
