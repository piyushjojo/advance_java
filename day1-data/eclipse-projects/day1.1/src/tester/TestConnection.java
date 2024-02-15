package tester;
import static utils.DBUtils.*;
import java.sql.*;

public class TestConnection {

	public static void main(String[] args) {
		try(Connection conn=openConnection())
		{
			System.out.println("db conn established "+conn);
		} //conn.close()
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
