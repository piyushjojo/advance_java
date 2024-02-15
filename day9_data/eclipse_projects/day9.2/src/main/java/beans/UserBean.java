package beans;

import java.sql.SQLException;

import dao.UserDaoImpl;
import pojos.User;

public class UserBean {
	// state
	private String email;
	private String password;
	// dependency : user dao
	private UserDaoImpl userDao;
	// to store validated user details
	private User validatedUserDetails;
	//add message in jb as status message : 
	private String message ; 

	// def ctor
	public UserBean() throws SQLException {
		// create dao instance
		userDao = new UserDaoImpl();
		System.out.println("user bean creted....");
	}

	// getters n setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDaoImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	public User getValidatedUserDetails() {
		return validatedUserDetails;
	}

	public void setValidatedUserDetails(User validatedUserDetails) {
		this.validatedUserDetails = validatedUserDetails;
	}

	// Add B.L: method for user login : typically return dyn. navigational
	// outcome(i.e where to navigate clnt)
	public String validateUser() throws SQLException {
		System.out.println("in auth user " + email + " " + password);// not null : provided u have matched req param
																		// names to JB property setters
		// JB invokes Dao's layer
		validatedUserDetails = userDao.authenticateUser(email, password);
		if (validatedUserDetails == null){ // => invalid login
			message = "Inavlid Login, please retry.";
			return "login";
		}
		// => valida login ---> role based auth
		message = "Inavlid Login, please retry.";
		if (validatedUserDetails.getRole().equals("ADMIN"))
			return "admin";
		return "topics";
	}

}
