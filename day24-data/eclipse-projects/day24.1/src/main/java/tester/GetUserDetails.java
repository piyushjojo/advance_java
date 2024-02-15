package tester;

import static utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;
import pojos.User;

public class GetUserDetails {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)) {
			UserDaoImpl dao = new UserDaoImpl();
			System.out.println("Enter user id");
			User user = dao.getUserDetails(sc.nextInt());
			if(user != null) {
			//user : detached
			System.out.println(user.getClass());
			System.out.println(user);
			} else
				System.out.println("invalid id !!!!!!!!!!!!!");
		} // sf.close() => immediate closing of cn pool , sc.close
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
