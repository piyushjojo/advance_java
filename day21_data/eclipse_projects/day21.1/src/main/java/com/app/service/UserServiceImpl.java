package com.app.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
	//auto wire model mapper dependency
	@Autowired
	private ModelMapper mapper;//field name NEED NOT match bean method name!

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
		//user : PERSISTENT
	//	UserLoginResponse resp = new UserLoginResponse(user.getFirstName(), user.getLastName(), user.getEmail());
		//API of ModelMapper : public Object map(Object src,Class<T> destType)
		//Mapping from Entity --> DTO
		UserLoginResponse resp = mapper.map(user, UserLoginResponse.class);
		//map Set<Role> ---> Set<String> to be sent to rhe clnt
		user.getRoles() //Set<Role>
		.forEach(role -> resp.getRoleNames().add(role.getRoleName().name()));		
		return resp;
	}

}
