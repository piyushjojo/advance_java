package tester;

import java.sql.*;
import java.util.Scanner;

import static utils.DBUtils.openConnection;

public class TestPreparedStatement {

	public static void main(String[] args) {
		String sql = "select empid,name,salary,join_date from my_emp where deptid=? and join_date between ? and ?";
		try (Connection cn = openConnection();
				Scanner sc = new Scanner(System.in);
				PreparedStatement pst = cn.prepareStatement(sql);

		) {
			System.out.println("Enter dept id , begin date n end date");
			// set IN params
			// API of PST : public void setType(int paramIndex,Type value) throws SqlExc
			pst.setString(1, sc.next());// dept
			pst.setDate(2, Date.valueOf(sc.next()));// begin date
			pst.setDate(3, Date.valueOf(sc.next()));// end date
			//exec query : execQuery ---> RST
			try(ResultSet rst=pst.executeQuery())
			{
				//rst cursor : before 1st row
				while(rst.next())
					System.out.printf("Emp Id %d Name %s  Salary %.2f Joined on %s%n", rst.getInt(1),
							rst.getString(2),  rst.getDouble(3),  rst.getDate(4));					
			}//rst.close

		} //pst.close , sc.close , cn.close
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
