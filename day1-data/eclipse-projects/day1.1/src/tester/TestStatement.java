package tester;

import static utils.DBUtils.*;
import java.sql.*;

public class TestStatement {
//id name sal joindate
	public static void main(String[] args) {
		try (Connection conn = openConnection();
				Statement st = conn.createStatement();
				ResultSet rst = st.executeQuery("select * from my_emp order by salary desc")) {
			// process RST
			while (rst.next())
				System.out.printf("Emp Id %d Name %s Address %s Salary %.2f Dept %s Joined on %s%n", rst.getInt(1),
						rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getString(5), rst.getDate(6));
		} //rst.close , st.close , conn.close()
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
