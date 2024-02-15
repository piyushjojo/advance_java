package com.app.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserLoginResponse {
	private String firstName;
	private String lastName;
	private String email;
	// how to send roles to the front end ?
	private Set<String> roles = new HashSet<>();
	public UserLoginResponse(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	
}
