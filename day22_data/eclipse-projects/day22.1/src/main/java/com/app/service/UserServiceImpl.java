package com.app.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.AddressRepository;
import com.app.dao.CommentRepository;
import com.app.dao.RoleRepository;
import com.app.dao.TagTutorialRepository;
import com.app.dao.TutorialRepository;
import com.app.dao.UserRepository;
import com.app.dto.ApiResponse;
import com.app.dto.UserLoginRequest;
import com.app.dto.UserLoginResponse;
import com.app.dto.UserSignupRequest;
import com.app.entities.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements IUserService {
	// dep : dao i/f
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AddressRepository addressRepo;

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private TutorialRepository tutRepo;
	
	@Autowired
	private TagTutorialRepository tagTutRepo;

	// auto wire model mapper dependency
	@Autowired
	private ModelMapper mapper;// field name NEED NOT match bean method name!

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public UserLoginResponse login(UserLoginRequest request) {
		User user = userRepo.findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!!"));
		// => valid login
		// user : PERSISTENT
		// API of ModelMapper : public Object map(Object src,Class<T> destType)
		// Mapping from Entity --> DTO
		UserLoginResponse resp = mapper.map(user, UserLoginResponse.class);
		// map Set<Role> ---> Set<String> to be sent to rhe clnt
		user.getRoles() // Set<Role>
				.forEach(role -> resp.getRoleNames().add(role.getRoleName().name()));
		return resp;
	}

	@Override
	public String deleteUserDetails(long userId) {
		// child rec addr
		if (addressRepo.existsById(userId))
			addressRepo.deleteById(userId);
		tagTutRepo.deleteByTutorialAuthorId(userId);		
		
		commentRepo.deleteByCustomerId(userId);
		
		tutRepo.deleteByAuthorId(userId);

		

		userRepo.deleteById(userId);
		return "deleted user details with id" + userId;
	}

	@Override
	public ApiResponse registerNewUser(UserSignupRequest userDto) {
		// map user signup dto --> user entity for persistence (model mapper will retain
		// default values for the fields which are not matching between dto n entity)
		User transientUser = mapper.map(userDto, User.class);
		log.info("user dto {} transient user {} ", userDto, transientUser);
		transientUser.setRoles(roleRepo.findByRoleNameIn(userDto.getUserRoles()));
		log.info("roles {}",transientUser.getRoles());
		User persistentUser = userRepo.save(transientUser);
		return new ApiResponse("User registered with ID " + persistentUser.getId() + " successfully!");
	}

	

}
