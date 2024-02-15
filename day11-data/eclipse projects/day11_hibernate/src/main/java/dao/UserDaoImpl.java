package dao;

import static utils.HibernateUtils.getSf;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Role;
import pojos.User;

public class UserDaoImpl implements IUserDao {
//no state , no ctro , no cleanup
	@Override
	public String addNewUser(User user) {
		// user : transient (exist only in heap)
		String message = "Adding user details failed!!!!!!!!!!!!!!";
		// get session from SF
		Session session = getSf().openSession();
		Session session2 = getSf().openSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		System.out.println("same sessions ?" + (session == session2));// f
		System.out.println("is open " + session.isOpen() + " is connected to db " + session.isConnected());// t t

		try {
			Integer id = (Integer) session.save(user);
			// user : persistent -> user ref added in l1 cache , but not yet part of
			// database.
			System.out.println("auto generated id " + id);
			tx.commit();// DML : insert
			message = "New user details added with ID " + id;
			System.out.println(
					"after commit : is open " + session.isOpen() + " is connected to db " + session.isConnected());// t
																													// t

		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback(); // L1 cache destroyed n pooled out of db cn returns to pool
			// to inform the caller : re throw the exc to the caller
			throw e;
		} finally {
			if (session != null)
				session.close();// L1 cache is destroyed n pooled out db cn rets to the pool
		}
		System.out.println("is open " + session.isOpen() + " is connected to db " + session.isConnected());// f f

		return message;
	}

	@Override
	public String addNewUserWithCurntSession(User user) {
		String message = "Adding user details failed!!!!!!!!!!!!!!";
		// get session from SF
		Session session = getSf().getCurrentSession();
		Session session2 = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		System.out.println("same sessions ?" + (session == session2));// t
		System.out.println("is open " + session.isOpen() + " is connected to db " + session.isConnected());// t t

		try {
			Integer id = (Integer) session.save(user);
			System.out.println("auto generated id " + id);
			tx.commit();// hib calls session.flush() -> automatic dirty checking -> DML : insert , L1
						// cache is destroyed n pooled out db cn rets to the pool
			message = "New user details added with ID " + id;
			System.out.println(
					"after commit : is open " + session.isOpen() + " is connected to db " + session.isConnected());// f
																													// f

		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();// L1 cache is destroyed n pooled out db cn rets to the pool
			System.out.println("is open " + session.isOpen() + " is connected to db " + session.isConnected());// f f

			// to inform the caller : re throw the exc to the caller
			throw e;
		}
		// detach
		return message;
	}

	public User getUserDetails(int userId) {
		User user = null;
		// get session from session factory
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();

		try {

			// session API : public <T> T get(Class<T> cls , Serializable id)

			user = session.get(User.class, userId);// even if repeated . only one result returned (hib only)
			tx.commit();// otherwise connection will not close .. resource leak .. and will not return
						// to pool.
		} catch (RuntimeException e) { // all hib exceptions originate from runtime
			if (tx != null) {
				tx.rollback();
			}
			// re throw the exception
			throw e;
		}

		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = null;
		// get session from session factory
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();

		try {
			String jpql = "select u from User u";
			//create a query --> exec the same (select) --> getResultList 
			users = session.createQuery(jpql, User.class).getResultList(); // return list of persistent entities. 
			
			tx.commit();
		} catch (RuntimeException e) { // all hib exceptions originate from runtime
			if (tx != null) {
				tx.rollback();
			}
			// re throw the exception
			throw e;
		}
		return users; // return list of detached entities. 
	}

	@Override
	public List<User> getSelectedUsers(LocalDate start, LocalDate end, Role role) {
		String jpql = "select u from User u where u.regDate between :start_date and :end_Date and u.userRole=:rl";
		List<User> users = null;
		//get session from sF
		Session session = getSf().getCurrentSession();
		
		//transaction
		Transaction tx = session.beginTransaction();
		try{
			users = session.createNamedQuery(jpql, User.class).setParameter("start_date", start)
					.setParameter("end_Date", end)
					.setParameter("rl", role)
					.getResultList(); // users : list of Persistent entities
			tx.commit();
		}catch(RuntimeException e) {
			if(tx != null) {
				tx.rollback();
			}
			throw e;
		}
		return users; // users : list of Detached entities
	}

}
