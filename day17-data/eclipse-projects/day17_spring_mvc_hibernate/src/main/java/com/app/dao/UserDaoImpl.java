package com.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.User;
import org.hibernate.*;

@Repository // MANDATORY : to tell SC , following is a spring bean containing DAL , Enables
			// exc translation (chked/un chked ---> spring specific un chked excs)
public class UserDaoImpl implements IUserDao {
	//dependency : SF
	@Autowired
	private SessionFactory sf;

	@Override
	public User authenticateUser(String email, String pass) {
		String jpql="select u from User u join fetch u.roles where u.email=:em and u.password=:pass";
		return sf.getCurrentSession()
				.createQuery(jpql, User.class)
				.setParameter("em", email)
				.setParameter("pass", pass)
				.getSingleResult();
	}//rets user + roles : in single seelct query

}
