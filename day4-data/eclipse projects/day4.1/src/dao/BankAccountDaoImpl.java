package dao;

import java.sql.*;
import static utils.DBUtils.*;

public class BankAccountDaoImpl implements IBankAccountDao {
	//state
	private Connection cn;
	private CallableStatement cst1;
	
	public BankAccountDaoImpl() throws SQLException{
		// open db conn
		cn=openConnection();
		cst1=cn.prepareCall("{call transfer_funds(?,?,?,?,?)}");
		System.out.println("acct dao created ");
		//shall we set IN params here ? NO : since it's init phase ---no clnts around !
		//Can we register OUT params here ? YES : decided by proc /func
		cst1.registerOutParameter(4, Types.DOUBLE);
		cst1.registerOutParameter(5, Types.DOUBLE);		
	}

	@Override
	public String transferFunds(int srcId, int destId, double amount) throws SQLException {
		// servicing phase (req processing phase)
		//set IN params
		cst1.setInt(1, srcId);
		cst1.setInt(2, destId);
		cst1.setDouble(3, amount);
		//exec stored proc : public boolean execute() throws SqlExc
		cst1.execute();//boolean ret value is ignored here.
		
		return "Updated src balance "+cst1.getDouble(4)+" dest balance "+cst1.getDouble(5);
	}
	
	public void cleanUp() throws SQLException
	{
		if(cst1 != null)
			cst1.close();
		if(cn != null)
			cn.close();
		System.out.println("acct dao cleaned up ...");
	}

}
