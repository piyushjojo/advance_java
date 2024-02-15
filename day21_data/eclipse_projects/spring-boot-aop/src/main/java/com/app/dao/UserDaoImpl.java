package com.app.dao;


import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.User;

@Repository // to tell SC : following class contains data access logic + enables exc
			// translation mechanism
public class UserDaoImpl implements IUserDao {
	// dependency : D.I
	@Autowired // byType
	private EntityManager manager;

	@Override
	public User validateUser(String email, String pass) {
		String jpql="select u from User u where u.email=:em and u.password=:pass";
		
		return manager.
				createQuery(jpql, User.class).
				setParameter("em", email).
				setParameter("pass",pass).getSingleResult();
	}

}
