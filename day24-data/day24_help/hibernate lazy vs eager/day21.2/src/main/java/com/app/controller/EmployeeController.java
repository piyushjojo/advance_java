package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.EmployeeDTO;
import com.app.entities.Employee;
import com.app.service.IEmployeeService;

@RestController // MANDATORY : composed of @Controller at the cls level + @ResponseBody(for
				// marshalling : java ---> json) addedd implicitly on ret types of all req
				// handling methods , annotated by @ReqMapping / @GetMapping .......
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class EmployeeController {
	// dep : emp service i/f
	@Autowired
	private IEmployeeService empService;

	public EmployeeController() {
		System.out.println("in ctor of " + getClass());
	}

	// add req handling method (REST API call) to send all emps
	@GetMapping
	public ResponseEntity<?> listAllEmps() {
		System.out.println("in list emps");
		List<Employee> list = empService.getAllEmpDetails();
		// o.s.ResponseEntity(T body,HttpStatus sts)
		if (list.isEmpty())
			return new ResponseEntity<>("Empty Emp List !!!!", HttpStatus.OK);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// add req handling method to create new emp
	@PostMapping
	public ResponseEntity<EmployeeDTO> saveEmpDetails(@RequestBody @Valid EmployeeDTO emp)
	// To inform SC , to un marshall(de-serialization , json/xml --> Java obj) the
	// method arg.
	{
		System.out.println("in save emp " + emp);// id : null...
//		return  ResponseEntity.ok(empService.saveEmpDetails(emp));

		return new ResponseEntity<>(empService.saveEmpDetails(emp), HttpStatus.CREATED);
	}

	// add req handling method to delete emp details
	@DeleteMapping("/{empId}") // can use ANY name for a path var.
	// @PathVariable => a binding between a path var to method arg.
	public String deleteEmpDetails(@PathVariable @Range(min = 1, max = 100, message = "Invalid emp id!!!") int empId) {
		System.out.println("in del emp " + empId);
		return empService.deleteEmpDetails(empId);
	}

	// add a method to get specific emp dtls
	@GetMapping("/{id}")
	// @PathVariable => a binding between a path var to method arg.
	public ResponseEntity<?> getEmpDetails(@PathVariable int id) {
		System.out.println("in get emp " + id);
		Employee employee = empService.getEmpDetails(id);
		System.out.println("emp class " + employee.getClass());
		return ResponseEntity.ok(employee);

	}

	// add a method to update existing resource
	@PutMapping
	public Employee updateEmpDetails(@RequestBody Employee emp) {
		System.out.println("in update emp " + emp);// id not null
		return empService.updateEmpDetails(emp);
	}

	// add a method to upload image on the server side folder
	@PostMapping("/{empId}/image")
	public ResponseEntity<?> uploadImage(@PathVariable int empId, @RequestParam MultipartFile imageFile) {
		System.out.println("in upload image " + empId);
		System.out.println("uploaded img file name " + imageFile.getOriginalFilename() + " content type "
				+ imageFile.getContentType() + " size " + imageFile.getSize());
		return ResponseEntity.ok("testing file uploads, will be resumed in the lab....");
	}
}
