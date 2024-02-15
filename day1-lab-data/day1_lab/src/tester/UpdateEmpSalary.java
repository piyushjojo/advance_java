package tester;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import static utils.DBUtils.openConnection;

public class UpdateEmpSalary {

	public static void main(String[] args) {
		String sql = "update my_emp set salary=salary+? where empid=?";
		try (Scanner sc = new Scanner(System.in);
				Connection cn = openConnection();
				PreparedStatement pst = cn.prepareStatement(sql);) {
			System.out.println("Enter salary increment n emp id");
			// set IN params
			pst.setDouble(1, sc.nextDouble());// sal incr
			pst.setInt(2, sc.nextInt());
			// DML : API of PST : public int executeUpdate() throws SQLException
			int updateCount = pst.executeUpdate();
			if (updateCount == 1)
				System.out.println("Updated emp salary !");
			else
				System.out.println("Updation failed!!!!!!!!!!!");
		} // pst cn sc : close
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
