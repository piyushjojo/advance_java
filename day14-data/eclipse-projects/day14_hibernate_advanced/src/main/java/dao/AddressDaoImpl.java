package dao;

import static utils.HibernateUtils.getSf;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Address;
import pojos.User;

public class AddressDaoImpl implements IAddressDao {

	@Override
	public String assignUserAddress(long userId, Address adr) {
		String mesg="assigning adr failed!!!!!!!!!!!!";
		// get session from SF
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			//1. get user details from user id
			User user=session.get(User.class, userId);
			//2. chk if user exists
			if(user != null)
			{
				//=> valid user id
				//establish uni dir link Address --> User
				adr.setOwner(user);
				session.persist(adr);	
				mesg="addr assigned to user with first name : "+user.getFirstName();
			}
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

}
