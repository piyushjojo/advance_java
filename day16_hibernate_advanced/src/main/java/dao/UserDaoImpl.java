package dao;

import static utils.HibernateUtils.getSf;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Role;
import pojos.User;

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
	public String registerUserWithRoles(User user, Set<Role> roles) {
		
		// get session from SF
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			// establish uni dire link from User ---> Role
			// call user.addRole(role) -- for Each roles
			// user.addRole(role) : role ---new Role(...) OR findByRole name from the role
			// dao layer ?
			// Stream<Role entity>
			roles.forEach(r -> user.addRole(r));
			System.out.println(user.getRoles());
			session.persist(user);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return "New User registered with ID " + user.getId();
	}

}
