package tester;

import static utils.HibernateUtils.getSf;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;

public class RestoreImage {

	public static void main(String[] args) {
		try (SessionFactory sf = getSf(); Scanner sc = new Scanner(System.in)) {
			// create dao instance
			UserDaoImpl dao = new UserDaoImpl();
			System.out.println("Enter user id");
			int userId=sc.nextInt();
			sc.nextLine();//reading off pending new line from the scanner
			System.out.println("Enter image file name along with path for restoring image from DB to a file");
			System.out.println(dao.restoreImage(userId, sc.nextLine()));

		} // sf.close() => cleaning up db cn pool !
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
