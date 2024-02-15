package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ITutorialDao;
import com.app.pojos.Tutorial;

@Service
@Transactional
public class TutorialServiceImpl implements ITutorialService {
	// dep : tut dao i/f
	@Autowired
	private ITutorialDao tutDao;

	@Override
	public List<String> getTutorialNamesByTopic(long topicId) {
		// TODO Auto-generated method stub
		return tutDao.getTutorialNamesByTopic(topicId);
	}

	@Override
	public Tutorial getTutorialDetails(String tutName) {
		Tutorial tutorial = tutDao.getTutorialDetails(tutName);
		// tutorial : PERSISTENT (MANAGED)
		// simply update state of persistent entity
		tutorial.setVisits(tutorial.getVisits() + 1);
		return tutorial;
	}// when @Tx method rets --- tx mgr chks for run time excs -- no excs --tx.commit
		// ---> session.flush --> hib auto dirty chking ---->update query ---pooled out
		// db cn rets to the pool , sesison close , l1 : destroyed

}
