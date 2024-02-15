package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
//Map this to existing tables "users"
@Table(name = "users")
public class User extends BaseEntity{
	// id | name    | email          | password | reg_amt | reg_date   | role
	@Column(length = 20)  //not needed if mapping to existing table /col
	private String name;
	@Column(length = 20,unique = true)
	private String email;
	@Column(length = 20)
	private String password;
	@Column(name="reg_amt")
	private double regAmount;
	@Column(name="reg_date")
	private LocalDate regDate;
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private UserRole role;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getRegAmount() {
		return regAmount;
	}
	public void setRegAmount(double regAmount) {
		this.regAmount = regAmount;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	
	public LocalDate getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "User [userId=" + getId() + ", name=" + name + ", email=" + email + ", regAmount=" + regAmount
				+ ", regDate=" + regDate + ", role=" + role + "]";
	}
	
	
}
