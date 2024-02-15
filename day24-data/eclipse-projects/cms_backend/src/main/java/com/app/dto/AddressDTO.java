package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class AddressDTO {
	private String adrLine1;

	private String adrLine2;

	private String city;

	private String state;

	private String country;
	private String zipCode;

//	public AddressDTO(String addressLine1, String addressLine2, String city, String state, String country) {
//		super();
//		this.adrLine1 = addressLine1;
//		this.adrLine2 = addressLine2;
//		this.city = city;
//		this.state = state;
//		this.country = country;
//	}
//
//	public AddressDTO(String userName, String addressLine1) {
//		super();
//		this.userName = userName;
//		this.adrLine1 = addressLine1;
//	}

}
