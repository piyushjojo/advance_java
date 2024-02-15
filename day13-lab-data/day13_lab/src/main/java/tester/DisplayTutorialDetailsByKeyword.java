package tester;

import static utils.HibernateUtils.getSf;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.TutorialDaoImpl;

public class DisplayTutorialDetailsByKeyword {

	public static void main(String[] args) {
		try (SessionFactory sf = getSf(); Scanner sc = new Scanner(System.in)) {
			// create tut dao instance
			TutorialDaoImpl dao = new TutorialDaoImpl();
			System.out.println("Enter keyword");
			dao.getTitleAndDateByKeyword(sc.next())
					.forEach(t -> System.out.println(t.getTitle() + " Published on " + t.getPublishDate()));

		} // sf.close() => cleaning up db cn pool !
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
