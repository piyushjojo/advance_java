package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import pojos.Employee;

public interface IEmployeeDao {
//add a method to return selected emp details by criteria
	/*
	 * fetch emp id , name , salary , join date of all emps from specific dept ,
	 * joined between start n end dates
	 */
	List<Employee> getEmpDetails(String deptName, Date begin, Date end) throws SQLException;

}
