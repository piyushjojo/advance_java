package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "adr_tbl")
@NoArgsConstructor
@Setter
@Getter
@ToString(exclude = "owner")
public class Address extends BaseEntity {
	// Address details : addrLine1, addrLine2,city,state country,zipCode
	@Column(length = 50, name = "adr_line1")
	private String adrLine1;
	@Column(length = 50, name = "adr_line2")
	private String adrLine2;
	@Column(length = 20)
	private String city;
	@Column(length = 20)
	private String state;
	@Column(length = 20)
	private String country;
	@Column(length = 20, name = "zip_code")
	private String zipCode;
	// one to one uni dir asso between User <----- Address
	// parent side : User , child : address
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	@MapsId // To tell hibernate , Address wil NOT have a separate PK , it will be shared
			// with users table n it will acts as FK referencing PK of the users table
	private User owner;

//	public Address(String adrLine1, String adrLine2, String city, String state, String country, String zipCode) {
//		super();
//		this.adrLine1 = adrLine1;
//		this.adrLine2 = adrLine2;
//		this.city = city;
//		this.state = state;
//		this.country = country;
//		this.zipCode = zipCode;
//	}

}
