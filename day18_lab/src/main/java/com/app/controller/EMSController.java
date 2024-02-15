package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.dao.IDepartmentDao;

@Controller
public class EMSController {
	//dep : dept dao i/f
	@Autowired
	private IDepartmentDao deptDao;
	
	public EMSController() {
		System.out.println("in ctor of "+getClass());
	}
	//add req handling method to show dept list
	@GetMapping("/")
	public String showDepartments(Model map)
	{
		System.out.println("in show depts");
		map.addAttribute("dept_list",deptDao.fetchAllDepartments());
		return "/dept";//AVN : /WEB-INF/views/dept.jsp
	}

}
