package tester;

import static utils.HibernateUtils.getSf;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.SessionFactory;

import dao.RoleDaoImpl;
import dao.UserDaoImpl;
import pojos.Role;
import pojos.User;
import pojos.UserRole;

public class UserRegistrationWithRoles {

	public static void main(String[] args) {
		try (SessionFactory sf = getSf(); Scanner sc = new Scanner(System.in)) {
			// create dao instance
			UserDaoImpl dao = new UserDaoImpl();
			RoleDaoImpl roleDao = new RoleDaoImpl();
			System.out.println("Enter user details : firstName,  lastName,  email,  password ");
			User user = new User(sc.next(), sc.next(), sc.next(), sc.next());
			sc.nextLine();
			System.out.println("Enter roles : admin , author , customer");
			// how to convert space separated strings --> set
			Set<UserRole> set = Arrays.stream(sc.nextLine().split(" ")) // Stream<String>
					.map(s -> UserRole.valueOf(s.toUpperCase())) // Stream<UserRole :enum>
					.collect(Collectors.toSet());// Set<UserRole>
			// convert Set<UserRole> --> Set<Role>
			Set<Role> roles = set.stream() // Stream<UserRole: enum>
					.map(r -> roleDao.findByRoleName(r))// Stream<Role entity>
					.collect(Collectors.toSet());
			System.out.println("roles " +roles);
			System.out.println(dao.registerUserWithRoles(user, roles));

		} // sf.close() => cleaning up db cn pool !
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
