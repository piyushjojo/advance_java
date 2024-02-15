package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Role extends BaseEntity {
	@Enumerated(EnumType.STRING)
	@Column(name = "role_name", length = 20,unique = true)
	private UserRole roleName;

	public Role(UserRole roleName) {
		super();
		this.roleName = roleName;
	}
	
}
