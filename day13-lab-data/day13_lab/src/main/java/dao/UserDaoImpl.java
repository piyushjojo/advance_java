package dao;

import pojos.User;
import static utils.HibernateUtils.getSf;
import org.hibernate.*;

public class UserDaoImpl implements IUserDao {

	@Override
	public String registerUser(User user) {
		// get session from SF
		Session session=getSf().getCurrentSession();
		//begin tx
		Transaction tx=session.beginTransaction();
		try {
			session.persist(user);
			tx.commit();
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}
		return "New User registered with ID "+user.getId();
	}

}
