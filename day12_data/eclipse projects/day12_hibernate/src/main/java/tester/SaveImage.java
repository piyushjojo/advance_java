package tester;

import static utils.HibernateUtils.getSf;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;
import static java.time.LocalDate.parse;
import static pojos.Role.valueOf;

public class SaveImage {

	public static void main(String[] args) {
		try (SessionFactory sf = getSf(); Scanner sc = new Scanner(System.in)) {
			// create dao instance
			UserDaoImpl dao = new UserDaoImpl();
			System.out.println("Enter user id");
			int userId=sc.nextInt();
			sc.nextLine();//reading off pending new line from the scanner
			System.out.println("Enter image file name along with path");
			System.out.println(dao.saveImage(userId, sc.nextLine()));

		} // sf.close() => cleaning up db cn pool !
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
