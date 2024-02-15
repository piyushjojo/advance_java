package com.app.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.Tutorial;

@Repository
public class TutorialDaoImpl implements ITutorialDao {
	// dep ; Entity Mgr
	@Autowired
	private EntityManager mgr;

	@Override
	public List<String> getTutorialNamesByTopic(long topicId) {
		String jpql = "select t.title from Tutorial t join t.topic tp where tp.id=:id";
		return mgr.createQuery(jpql, String.class).setParameter("id", topicId).getResultList();
	}

	@Override
	public Tutorial getTutorialDetails(String tutName) {
		String jpql = "select t from Tutorial t where t.title=:title";
		return mgr.createQuery(jpql, Tutorial.class).setParameter("title", tutName).getSingleResult();
	}

}
