package tester;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import static utils.DBUtils.openConnection;

public class InsertEmpDetails {

	public static void main(String[] args) {
		String sql = "insert into my_emp values(default,?,?,?,?,?)";
		try (Scanner sc = new Scanner(System.in);
				Connection cn = openConnection();
				PreparedStatement pst = cn.prepareStatement(sql);) {
			System.out.println("Enter emp details name adr salary dept join date(yr-mon-day)");
			// set IN params
			pst.setString(1, sc.next());//name
			pst.setString(2, sc.next());//address
			pst.setDouble(3, sc.nextDouble());//sal
			pst.setString(4, sc.next());//dept
			pst.setDate(5,Date.valueOf(sc.next()));//join date
			
			// DML : API of PST : public int executeUpdate() throws SQLException
			int updateCount = pst.executeUpdate();
			if (updateCount == 1)
				System.out.println("Inserted emp details ....");
			else
				System.out.println("Insertion failed!!!!!!!!!!!");
		} // pst cn sc : close
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
