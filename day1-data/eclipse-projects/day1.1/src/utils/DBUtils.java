package utils;
import java.sql.*;

public class DBUtils {
	private static Connection cn;
//add a static method to open FIXED DB connection
	public static Connection openConnection() throws /* ClassNotFoundException, */SQLException
	{
		//load n optionally register Type IV JDBC Driver
	//	Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/dac22?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
		//get cn
		cn=DriverManager.getConnection(url, "root", "piyush");
		return cn;
		
	}
	//add a static method to return db cn to the caller
	public static Connection getCn() {
		return cn;
	}
	//add a static method to close db cn
	public static void closeConnection() throws SQLException{
		if(cn != null)
			cn.close();
	}
	
	
	
}
