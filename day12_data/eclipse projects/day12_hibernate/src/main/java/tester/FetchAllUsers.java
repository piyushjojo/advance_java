package tester;

import static utils.HibernateUtils.getSf;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;

public class FetchAllUsers {

	public static void main(String[] args) {
		try (SessionFactory sf = getSf()) {
			// create dao instance
			UserDaoImpl dao = new UserDaoImpl();
			dao.getAllUsers().forEach(System.out::println);

		} // sf.close() => cleaning up db cn pool !
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
