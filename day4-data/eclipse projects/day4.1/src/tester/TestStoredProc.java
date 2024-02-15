package tester;

import java.util.Scanner;

import dao.BankAccountDaoImpl;

public class TestStoredProc {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			// create acct dao instance
			BankAccountDaoImpl dao = new BankAccountDaoImpl();
			System.out.println("Enter src acct no , dest acct no n amount");
			System.out.println(dao.transferFunds(sc.nextInt(), sc.nextInt(), sc.nextDouble()));
			dao.cleanUp();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
