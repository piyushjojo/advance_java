package dao;

import pojos.Role;
import pojos.User;

import org.apache.commons.io.FileUtils;
import org.hibernate.*;
import static utils.HibernateUtils.getSf;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class UserDaoImpl implements IUserDao {
//no state , no ctro , no cleanup
	@Override
	public String addNewUser(User user) {
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
			System.out.println("auto generated id " + id);
			tx.commit();// DML : insert
			message = "New user details added with ID " + id;
			System.out.println(
					"after commit : is open " + session.isOpen() + " is connected to db " + session.isConnected());// t
																													// t

		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
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
		// user : TRANSIENT (exists only in obj heap)
		String message = "Adding user details failed!!!!!!!!!!!!!!";
		// get session from SF
		Session session = getSf().getCurrentSession();
		Session session2 = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		System.out.println("same sessions ?" + (session == session2));// t
		System.out.println("is open " + session.isOpen() + " is connected to db " + session.isConnected());// t t

		try {
			// Integer id = (Integer) session.save(user);
			session.persist(user);
			// user : PERSISTENT (=> user ref added in L1 cache , BUT not yet part of DB)
			System.out.println("auto generated id " + user.getUserId());
			tx.commit();// hib session.flush() --> auto dirty chking --> DML : insert , L1 cache is
						// destroyed n pooled out db cn rets to the pool
			// user : DEATCHED (i.e detached from L1 cache) BUT part of DB
			message = "New user details added with ID " + user.getUserId();
			System.out.println(
					"after commit : is open " + session.isOpen() + " is connected to db " + session.isConnected());// f
																													// f

		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();// L1 cache is destroyed n pooled out db cn rets to the po

			System.out.println("is open " + session.isOpen() + " is connected to db " + session.isConnected());// f f

			// to inform the caller : re throw the exc to the caller
			throw e;
		}

		return message;
	}

	@Override
	public User getUserDetails(int userId) {
		User user = null;// state : NO valid state
		// get Session from SF
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			// Session API : public <T> T get(Class<T> cls , Serializable id)
			user = session.get(User.class, userId);// int --> Integer ---> Serializable : in case of valid id , user :
													// PERSISTENT
			user = session.get(User.class, userId);// int --> Integer ---> Serializable
			user = session.get(User.class, userId);// int --> Integer ---> Serializable
			user = session.get(User.class, userId);// int --> Integer ---> Serializable
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exc to the caller
			throw e;
		}
		return user;// user :DETACHED
	}

	@Override
	public List<User> getAllUsers() {
		String jpql = "select u from User u";
		List<User> users = null;
		// get Session from SF
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			// create a query --exec the same(select) --> getResultList
			users = session.createQuery(jpql, User.class).getResultList();// users => list of PERSISTENT entities
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exc to the caller
			throw e;
		}
		return users;// users => list of DETACHED entities
	}

	@Override
	public List<User> getSelectedUsers(LocalDate start, LocalDate end, Role role) {
		String jpql = "select u from User u where u.regDate between :start_date and :end_date and u.userRole=:rl";
		List<User> users = null;
		// get session from SF
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			users = session.createQuery(jpql, User.class).setParameter("start_date", start)
					.setParameter("end_date", end).setParameter("rl", role).getResultList();
			// users : List of PERSISTENT entities
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return users;// users : List of DETACHED entities
	}

	@Override
	public List<String> getUserNamesByDate(LocalDate date) {
		List<String> names = null;
		String jpql = "select u.firstName from User u where u.regDate > :dt";
		// get session from SF
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			names = session.createQuery(jpql, String.class).setParameter("dt", date).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return names;
	}

	/*
	 * Display all user's last name,reg amount,reg date registered between strt date
	 * n end date
	 */
	@Override
	public List<User> getUserDetailsByProjection(LocalDate start, LocalDate end1) {
		List<User> users = null;
		String jpql = "select new pojos.User(lastName,regAmount,regDate) from User u where u.regDate between :strt and :end";
		// get session from SF
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			users = session.createQuery(jpql, User.class).setParameter("strt", start).setParameter("end", end1)
					.getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return users;
	}

	@Override
	public String changePassword(int userId, String newPwd) { // 5678
		User user = null;
		String mesg = "Changing pwd failed !!!!!!!!!!!!";
		// get session from SF
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			user = session.get(User.class, userId);// int --> Integer
			if (user != null) {
				// user : PERSISTENT
				user.setPassword(newPwd);// changing state of the PERSISTENT entity
				mesg = "Password updated successfully for user : " + user.getLastName();
				// session.evict(user);//user : DETACHED : test it in the lab !
			}
			tx.commit();// session.flush --> auto dirty chking --> update query --> L1 cache destroyed ,
						// cn rets to the pool --> session closing
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		user.setPassword("999999999");// changing state of the DETACHED entity => no changes in the DB !
		return mesg;
	}

	@Override
	public String bulkDiscount(LocalDate date, double discount) {
		String mesg = "Bulk updation failed!!!!!!!!!!!!!";
		String jpql = "update User u set u.regAmount=u.regAmount-:disc where u.regDate < :dt";
		// get session from SF
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			int updateCount = session.createQuery(jpql).setParameter("disc", discount).setParameter("dt", date)
					.executeUpdate();
			mesg = updateCount + " user updated !";
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

	@Override
	public String unsubscribeUser(String email1) {
		String mesg = "Un subscription failed !!!!!!!!!!!!!!!";
		String jpql = "select u from User u where u.email=:em";
		// get session from SF
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			User user = session.createQuery(jpql, User.class).setParameter("em", email1).getSingleResult();
			// => user : PERSISTENT
			session.delete(user);// user : REMOVED (BUT neither gone from L1 cache nor from DB)
			tx.commit();// flush ---> dirty chking --> delete query --> l1 cache destroyed --cn rets to
						// the pool
			// user : TRANSIENT
			mesg = "user with last name : " + user.getLastName() + " un subscribed!";
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	} // user : Marked for GC

	@Override
	public String saveImage(int userId, String imagePath) throws IOException {
		StringBuilder sb = new StringBuilder("Saving Image ");
		// get session from SF
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			// 1. get user details from user id
			User user = session.get(User.class, userId);
			if (user != null) {
				// user : PERSISTENT
				// validate path
				File path = new File(imagePath);
				if (path.isFile() && path.canRead()) {
					// => valid path
					user.setImage(FileUtils.readFileToByteArray(path));
					// => success
					sb.append("succeeded !");
				} else
					sb.append("failed : Invalid File name!!!!!!!!");
			} else
				sb.append(" failed : Invalid user id !!!!!!!!!!!!!");
			tx.commit();// update query
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return sb.toString();
	}

	@Override
	public String restoreImage(int userId, String imagePath) throws IOException {
		StringBuilder sb = new StringBuilder("Restoring Image ");
		// get session from SF
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			// 1. get user details from user id
			User user = session.get(User.class, userId);
			if (user != null) {
				// user : PERSISTENT
				FileUtils.writeByteArrayToFile(new File(imagePath), user.getImage());
				sb.append("successful!");
				
			} else
				sb.append(" failed : Invalid user id !!!!!!!!!!!!!");

			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return sb.toString();
	}

}
