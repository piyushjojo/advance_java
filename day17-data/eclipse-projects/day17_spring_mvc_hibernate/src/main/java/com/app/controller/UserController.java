package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.pojos.Role;
import com.app.pojos.User;
import com.app.pojos.UserRole;
import com.app.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	// dependency : service layer i/f
	@Autowired
	private IUserService userService;

	public UserController() {
		System.out.println("in ctor of " + getClass());
	}

	// add req handling method for showing login form
	@GetMapping("/login")
	public String showLoginForm() {
		System.out.println("in show login form");
		return "/user/login";// AVN : /WEB-INF/views/user/login.jsp
	}

	// add req handling method for processign the login form
	@PostMapping("/login")
	// @RequestParam : anno for binding req param ---> method arg
	// String email =request.getParameter("email") , String pass
	// =request.getParameter("password")
	public String processLoginForm(@RequestParam String email, @RequestParam(name = "password") String pass,
			Model map) {
		System.out.println("in process login form " + email + " " + pass + " " + map);
		try {
			// controller --> service for B.L
			User authenticatedUser = userService.authenticateUser(email, pass);// authenticatedUser : DETACHED
			map.addAttribute("user_dtls", authenticatedUser);
			map.addAttribute("mesg", "Login Successful");
			// proceed to role based auth.
			// chk if user is under admin role --forward to /admin/status
			if (authenticatedUser.getRoles().contains(new Role(UserRole.ADMIN)))
				return "/admin/status";
			// chk if user is under customer role --forward to /customer/topics
			if (authenticatedUser.getRoles().contains(new Role(UserRole.CUSTOMER)))
				return "/customer/topics";

			// => valid login
			// add validated user details under suitable scope n forward clnt to success.jsp
			return "/user/success"; // AVN : /WEB-INF/views/user/success.jsp
		} catch (RuntimeException e) {
			System.out.println("err in " + getClass() + " " + e);// NoResExc
			// add err mesg in the model attr map
			map.addAttribute("mesg", "Invalid Login , Please retry.....");
			return "/user/login";// in case of invalid login --> forwarding the clnt to login.jsp , in the SAME
									// req
		}

	}
}
