package com.app.service;

import com.app.pojos.User;

public interface IUserService {
	// add a method for user authentication
	User authenticateUser(String email, String pass);
}
