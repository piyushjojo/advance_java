package com.app.dao;

import java.util.List;

import com.app.pojos.Employee;

public interface IEmployeeDao {
	List<Employee> getEmpsByDeptId(long deptId);
}
