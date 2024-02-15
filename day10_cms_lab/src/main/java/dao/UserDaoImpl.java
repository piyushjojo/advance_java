package dao;

import java.sql.*;
import static utils.DBUtils.*;

import pojos.User;

public class UserDaoImpl implements IUserDao {
	// state
	private Connection cn;
	private PreparedStatement pst1;

	public UserDaoImpl() throws SQLException {
		// open connection will be invoked once from init() of the servlet
		// get existing cn
		cn = getCn();
		// pst : auth
		pst1 = cn.prepareStatement("select * from users where email=? and password=?");
		System.out.println("user dao created ....");
	}

	@Override
	public User authenticateUser(String email, String password) throws SQLException {
		// set IN params
		pst1.setString(1, email);
		pst1.setString(2, password);
		System.out.println("exec auth query");
		try (ResultSet rst = pst1.executeQuery()) {
			
			if (rst.next())
				return new User(rst.getInt(1), rst.getString(2), email, password, rst.getDouble(5), rst.getDate(6),
						rst.getString(7));
		}

		return null;
	}

	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		System.out.println("user dao cleaned up");
	}

}
