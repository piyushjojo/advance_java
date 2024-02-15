package com.app.dao;

import com.app.pojos.User;

public interface IUserDao {
//add a method for user authentication
	User authenticateUser(String email,String pass);
}
