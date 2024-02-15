package com.app.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddressDTO;
import com.app.entities.Address;
import com.app.service.AddressService;

@RestController
@RequestMapping("/users")
//@RequestMapping("/users/{userId}/address")
public class AddressController {
//	@Autowired
	private AddressService addressService;
	//implicit constr based D.I 
	public AddressController(AddressService addressService) {
		this.addressService=addressService;
	}

	// add REST API end point to assign address details to existing user
	@PostMapping("/{userId}/address")
	public ResponseEntity<?> assignUserAddress(@PathVariable long userId, @RequestBody @Valid AddressDTO address) {
		System.out.println("in assign address " + address);
		return ResponseEntity.ok(addressService.assignAddress(userId,address));
	}
	
	//add REST end point to change city of user's address
	@PutMapping("/{userId}/address/city/{city}")
	public ResponseEntity<?> changeCity(@PathVariable long userId,@PathVariable String city) {
		System.out.println("in change city " +userId+" "+city);
		return ResponseEntity.ok(addressService.changeCity(userId, city));
	}
	
	//add REST end point to get  user's address
		@GetMapping("/{userId}/address")
		public ResponseEntity<?> getUserAddress(@PathVariable long userId) {
			System.out.println("in get user adr " +userId);
			return ResponseEntity.ok(addressService.getUserAddress(userId));
		}
	
	

}
