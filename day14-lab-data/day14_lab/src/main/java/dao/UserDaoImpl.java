package dao;

import static utils.HibernateUtils.getSf;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Role;
import pojos.User;
import pojos.UserRole;

public class UserDaoImpl implements IUserDao {

	@Override
	public String registerUser(User user) {
		// get session from SF
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			session.persist(user);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return "New User registered with ID " + user.getId();
	}

	@Override
	public String registerUserWithRoles(User user, Set<UserRole> roles) {
		String jpql = "select r from Role r where r.roleName in (:roles)";
		// get session from SF
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			Set<Role> rollSet = session.createQuery(jpql, Role.class).setParameter("roles", roles).getResultStream()
					.collect(Collectors.toSet());
			// establish uni dir link User ---> Role
			user.setRoles(rollSet);
			session.persist(user);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return "New User registered with ID " + user.getId();
	}

	@Override
	public List<User> getUsersByCity(String city) {
		List<User> users = null;
		String jpql = "select a.owner from Address a where a.city=:city";
		// get session from SF
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			users = session.createQuery(jpql, User.class).setParameter("city", city).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return users;
	}

}
