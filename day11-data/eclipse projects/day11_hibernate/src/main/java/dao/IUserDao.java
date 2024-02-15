package dao;

import java.time.LocalDate;
import java.util.List;

import pojos.Role;
import pojos.User;

public interface IUserDao {
	String addNewUser(User user);

	String addNewUserWithCurntSession(User user);

	// get user details by PK
	User getUserDetails(int userId);// auto boxing
	// get all user details

	List<User> getAllUsers();

	// get user details by some criteria
	List<User> getSelectedUsers(LocalDate start, LocalDate end, Role role);
}
