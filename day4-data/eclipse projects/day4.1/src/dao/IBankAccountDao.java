package dao;

import java.sql.SQLException;

public interface IBankAccountDao {
//transfer funds
	String transferFunds(int srcId,int destId,double amount) throws SQLException;
	
}
