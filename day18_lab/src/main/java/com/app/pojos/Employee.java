package com.app.pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="emps")
public class Employee extends BaseEntity{
	@Column(length = 30)
	private String name;
	@Column(length = 50)
	private String address;
	private double salary;
	@Column(name="join_date")
	private LocalDate joinDate;
	//add Dept  1<----* Emp , uni many to one relationship between 2 entities
	@ManyToOne
	@JoinColumn(name="dept_id", nullable = false)
	private Department department;
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public LocalDate getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}
	
	
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "Employee ID "+getId()+" [name=" + name + ", address=" + address + ", salary=" + salary + ", joinDate=" + joinDate
				+ "]";
	}
	

}
