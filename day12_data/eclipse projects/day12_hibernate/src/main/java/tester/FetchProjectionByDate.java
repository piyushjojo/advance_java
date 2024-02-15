package tester;

import static utils.HibernateUtils.getSf;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;
import static java.time.LocalDate.parse;
import static pojos.Role.valueOf;

public class FetchProjectionByDate {

	public static void main(String[] args) {
		try (SessionFactory sf = getSf(); Scanner sc = new Scanner(System.in)) {
			// create dao instance
			UserDaoImpl dao = new UserDaoImpl();
			System.out.println("Enter begin date(yyyy-MM-dd) , end date ");
			dao.getUserDetailsByProjection(parse(sc.next()), parse(sc.next())).
			forEach(System.out::println);

		} // sf.close() => cleaning up db cn pool !
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
