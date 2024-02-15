package dao;

import pojos.User;

public interface IUserDao {
	String saveUser(User user);
	String saveUserWithGetCurntSession(User user);
	User getUserDetails(int userId);
}
