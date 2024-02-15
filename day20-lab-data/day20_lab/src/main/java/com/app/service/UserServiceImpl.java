package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.UserRepository;
import com.app.dto.UserLoginRequest;
import com.app.dto.UserLoginResponse;
import com.app.entities.User;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	// dep : dao i/f
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public UserLoginResponse login(UserLoginRequest request) {
		User user = userRepo.findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!!"));
		//=> valid login
		UserLoginResponse resp = new UserLoginResponse(user.getFirstName(), user.getLastName(), user.getEmail());
		//map Set<Role> ---> Set<String>
		user.getRoles() //Set<Role>
		.forEach(role -> resp.getRoles().add(role.getRoleName().name()));		
		return resp;
	}

}
