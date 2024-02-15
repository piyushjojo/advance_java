package com.app.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.AddressRepository;
import com.app.dao.UserRepository;
import com.app.dto.AddressDTO;
import com.app.dto.ApiResponse;
import com.app.entities.Address;
import com.app.entities.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AddressServiceImpl implements AddressService {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AddressRepository adrRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public ApiResponse assignAddress(long userId, AddressDTO address) {
		log.info("Assgin adr : user id {} adr {} ", userId, address);
		// get user from it's id
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Invalid User ID "));
		// if (user.getAddress() == null) { // => if the address is not already assigned
		// , then only assign address
		// user.setAddress(mapper.map(address, Address.class));// since used cascade
		// type : persist , rec is inserted auto
		// map adr dto --> Address entity
		Address transientAdr = mapper.map(address, Address.class);
		// establish uni dir Address ---> User
		transientAdr.setOwner(user);
		adrRepo.save(transientAdr);
		return new ApiResponse("Address assigned for User " + user.getFirstName());
	}

	@Override
	public ApiResponse changeCity(long userId, String city) {
		log.info("change city  : user id {} city  {} ", userId, city);
		// get user from it's id
		Address address = adrRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid User/Address Id!!!!"));
		address.setCity(city);
		return new ApiResponse("Address updated for user  ");
	}

	@Override
	public AddressDTO getUserAddress(long userId) {
		log.info("get user adr   : user id {} ", userId);
//		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Invalid User Id!!!!"));
		AddressDTO addressDTO = mapper.map(
				adrRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Invalid Address Id!!!!")),
				AddressDTO.class);
//		addressDTO.setUserName(user.getFirstName()+" "+user.getLastName());
		return addressDTO;

	}

}
