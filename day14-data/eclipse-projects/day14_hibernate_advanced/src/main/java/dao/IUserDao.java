package dao;

import java.util.Set;

import pojos.Passport;
import pojos.Role;
import pojos.User;

public interface IUserDao {
//register user
	String registerUser(User user);
	//register user along with roles
	String registerUserWithRoles(User user,Set<Role> roles);
	
	
}
