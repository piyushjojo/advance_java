package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="departments")
public class Department extends BaseEntity {
	@Column(name="dept_name",unique = true,length = 30)
	private String deptName;
	@Column(length = 30)
	private String location;

	public Department() {
		// TODO Auto-generated constructor stub
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Department ID " + getId() + "[deptName=" + deptName + ", location=" + location + "]";
	}

}
