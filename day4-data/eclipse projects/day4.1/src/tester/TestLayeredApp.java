package tester;

import java.sql.Date;
import java.util.Scanner;

import dao.EmployeeDaoImpl;

public class TestLayeredApp {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			// create dao instance : init phase
			EmployeeDaoImpl empDao = new EmployeeDaoImpl();
			System.out.println("Enter dept n join date (yr-mon-day)");
			empDao.getEmpDetails(sc.next(), Date.valueOf(sc.next())).forEach(System.out::println);
			// clean up dao : destroy (shutting down)
			empDao.cleanUp();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
