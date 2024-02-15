package pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "users")

public class User extends BaseEntity {
	@Column(length = 20)
	private String name;
	@Column(length = 20, unique = true) // add unique constraint
	private String email;
	@Column(length = 20)
	private String password;
	@Column(name = "reg_date")
	private LocalDate regDate;
	@Column(name = "reg_amt")
	private double regAmount;
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private UserRole role;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String name, String email, String password, LocalDate regDate, double regAmount, UserRole role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.regDate = regDate;
		this.regAmount = regAmount;
		this.role = role;
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

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
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

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + ", regDate=" + regDate
				+ ", regAmount=" + regAmount + ", role=" + role + "]";
	}
	

}
