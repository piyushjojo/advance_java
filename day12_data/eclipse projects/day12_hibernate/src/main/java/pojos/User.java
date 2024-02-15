package pojos;

import java.time.LocalDate;
import javax.persistence.*;

/*
 * userId (PK) ,firstName,lastName,email,password,confirmPassword,role(enum), regAmount;
	 LocalDate/Date regDate;
	 byte[] image;
 */
@Entity // MANDATORY : to tell hibernate , following is an entity class (having it's own
		// DB identity)
@Table(name = "users_tbl") // to specify table name
public class User {
	@Id // mandatory , constraint : PK
	// @GeneratedValue//auto generation of PKs, def strategy for generating PKs
	// :GenerationType. AUTO=> db specific native strategy
	@GeneratedValue(strategy = GenerationType.IDENTITY) // extra constraint : auto incr : espacially for my sql/ reco
													// for oracle :seq generator
	@Column(name = "user_id")
	private Integer userId;// can be replaced by int --auto boxing
	@Column(name="first_name",length = 20)
	private String firstName;
	@Column(name="last_name",length = 30) //varchar(30)
	private String lastName;
	@Column(length = 30,unique = true) //add unique constraint
	private String email;
	@Column(length = 20, nullable = false) //NOT NULL constraint
	private String password;
	@Transient //skips the field from persistence (no col!)
	private String confirmPassword;
	@Enumerated(EnumType.STRING) //to specify col type : varchar => enum const name
	@Column(length = 20,name="user_role")
	private Role userRole;
	@Column(name="reg_amount")
	private double regAmount;
	@Column(name="reg_date")
	private LocalDate regDate;
	@Lob //same annotation for col type = clob / blob 
	private byte[] image;

	public User() {
		System.out.println("in user ctor");
	}

	public User(String firstName, String lastName, String email, String password, String confirmPassword, Role userRole,
			double regAmount, LocalDate regDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.userRole = userRole;
		this.regAmount = regAmount;
		this.regDate = regDate;
	}
	

	public User(String lastName, double regAmount, LocalDate regDate) {
		super();
		this.lastName = lastName;
		this.regAmount = regAmount;
		this.regDate = regDate;
	}

	// generate ALL setters n getters
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public double getRegAmount() {
		return regAmount;
	}

	public void setRegAmount(double regAmount) {
		this.regAmount = regAmount;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", userRole=" + userRole + ", regAmount=" + regAmount + ", regDate=" + regDate + "]";
	}

}
