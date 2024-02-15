package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString(exclude = "role")
@NoArgsConstructor
public class UserEntity extends BaseEntity {
	@Column(name = "first_name", length = 20)
	private String firstName;
	@Column(name = "last_name", length = 30) // varchar(30)
	private String lastName;
	@Column(length = 30, unique = true) // add unique constraint
	private String email;
	@Column(length = 400, nullable = false) // NOT NULL constraint
	private String password;
	// uni dir many to one relationship between entities : User *---->1 Role
	@ManyToOne // mandatory , o.w MappingExc
	// annotation below : optional BUT reco for customizing names of the link table
	// n it's cols
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;
}
