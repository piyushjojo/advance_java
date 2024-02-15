package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.EmployeeRepository;
import com.app.entities.Employee;

@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {
	//dep : dao i/f
	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public List<Employee> getAllEmpDetails() {
		System.out.println("emp repo "+empRepo.getClass());
		//to chk lombok
//		System.out.println(new Employee().getName());
		return empRepo.findAll();
	}

}
