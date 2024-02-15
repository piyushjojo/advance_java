package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@Getter
@Setter
public class Employee extends BaseEntity {
	@Column(length = 20)
	@NotBlank(message = "first name is required")
	private String name;
	@Column(length = 20,unique = true)
	@NotBlank(message = "last name is required")
	@Min(value = 4,message ="Last name min chars 4")
	@Max(value = 20,message ="Last name max  chars 20")	
	private String lastName;
	@Column(length = 20)
	@NotBlank(message = "email is required")
	@Email(message = "invalid email format")
	private String email;
	@Column(length = 20)
	@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})",message = "Blank or Invalid password")
	private String password;
	@Column(length = 20)
	@NotBlank(message = "location must be supplied")
	private String location;
	@Column(length = 20, name = "dept_name")
	@JsonProperty(value = "department")
	private String dept;
	@NotNull(message = "Salary must be supplied")
	@Range(min = 10000,max = 500000,message = "Salary outside the range")
	private double salary;
	@Future(message = "join date must be in future")
	private LocalDate joinDate;

}
