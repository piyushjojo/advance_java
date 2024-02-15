package tester;

import java.sql.Date;
import java.util.Scanner;

import dao.EmployeeDaoImpl;
import pojos.Employee;

public class TestLayeredApp2 {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			// create dao instance : init phase
			EmployeeDaoImpl empDao = new EmployeeDaoImpl();
			System.out.println("Enter emp details : name adr sal dept join date(yr-mon-day)");
			System.out.println(empDao.insertEmpDetails(
					new Employee(sc.next(), sc.next(), sc.nextDouble(), sc.next(), Date.valueOf(sc.next()))));
			// clean up dao : destroy (shutting down)
			empDao.cleanUp();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
