package com.app.service;

import java.util.List;

import javax.validation.Valid;

import com.app.dto.UserLoginRequest;
import com.app.dto.UserLoginResponse;
import com.app.entities.User;

public interface IUserService {
	List<User> getAllUsers();

	UserLoginResponse login(UserLoginRequest request);
}
