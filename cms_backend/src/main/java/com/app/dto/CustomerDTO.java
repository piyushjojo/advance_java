package com.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
	private String id;
	private String name;
	private String email;
	private String address;
	private int balance;
}
