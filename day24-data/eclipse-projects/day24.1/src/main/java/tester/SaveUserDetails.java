package tester;

import static utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;
import pojos.User;
import pojos.UserRole;

public class SaveUserDetails {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)) {
			// create user dao instance
			UserDaoImpl dao = new UserDaoImpl();
			System.out.println("Enter user details : name,  email,  password,  regDate,  regAmount,  role");
			User user = new User(sc.next(), sc.next(), sc.next(), LocalDate.parse(sc.next()), sc.nextDouble(),
					UserRole.valueOf(sc.next().toUpperCase()));
			System.out.println(dao.saveUser(user));
		} // sf.close() => immediate closing of cn pool
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
