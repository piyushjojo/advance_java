package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Employee;
import com.app.service.IEmployeeService;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
	//dep : service layer i/f
	@Autowired
	private IEmployeeService empService;
	
	public EmployeeController() {
		System.out.println("in ctor of " + getClass());
	}

//add REST API endpoint to ret list of all emps
	@GetMapping
	public List<Employee> listEmployees() {
		System.out.println("in list emps");
		return empService.getAllEmpDetails();
	}

}
