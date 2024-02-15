package com.app.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Topic;

@Repository
@Transactional
public class TopicDaoImpl implements ITopicDao {
	// dep : EM
	@Autowired
	private EntityManager em;

	@Override
	public List<Topic> getAllTopics() {
		// TODO Auto-generated method stub
		return em.createQuery("select t from Topic t", Topic.class).getResultList();
	}

}
