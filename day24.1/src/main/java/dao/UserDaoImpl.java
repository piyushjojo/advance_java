package dao;

import static utils.HibernateUtils.getFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.User;

public class UserDaoImpl implements IUserDao {

	@Override
	public String saveUser(User user) {
		// get hibernate session from SF using getCurrentSession API
		Session session = getFactory().getCurrentSession(); // L1 cache created along with hib session n db conn pooled
															// out
		// begin hibernate transaction(tx)
		Transaction tx = session.beginTransaction();
		try {
			// Session API : public Serializable save(Object entity)
			session.save(user);
			// => success --commit
			tx.commit();// hib fires insert query (DML)

		} catch (RuntimeException e) {
			// failure -- rollback
			if (tx != null)
				tx.rollback();
			throw e;// re throwing SAME exc to the caller for info.
		}
		return "User details inserted with user ID " + user.getId();
	}

	@Override
	public User getUserDetails(int userId) {
		User user = null;// user : does not exist!
		// get Session from SF
		Session session = getFactory().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			// API of Session : public <T> T get/load(Class<T> cls, Serializable id) throws
			// HibernateExc
			user = session.load(User.class, userId);// in case of valid id --user : PERSISTENT , has a corresponding rec
													// in DB n it's part of L1 cache
			user.getEmail();
			if(user != null) {
				System.out.println("user's class "+ user.getClass());
			}
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return user;// in case of valid id : user : DETACHED
	}

}
