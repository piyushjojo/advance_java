package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String processLoginForm(@RequestParam String email, @RequestParam(name = "password") String pass, Model map,
			HttpSession session, RedirectAttributes flashMap) {
		System.out.println("in process login form " + email + " " + pass + " " + map);
		try {
			// controller --> service for B.L
			User authenticatedUser = userService.authenticateUser(email, pass);// authenticatedUser : DETACHED
			session.setAttribute("user_dtls", authenticatedUser);// scope : entire session(multiple reqs coming from
																	// SAME clnt)
			flashMap.addFlashAttribute("mesg", "Login Successful");// scope : till next req
			// proceed to role based auth.
			// chk if user is under admin role --forward to /admin/status
			if (authenticatedUser.getRoles().contains(new Role(UserRole.ADMIN)))
				return "redirect:/admin/status";
			// chk if user is under customer role --forward to /customer/topics
			if (authenticatedUser.getRoles().contains(new Role(UserRole.CUSTOMER)))
				return "redirect:/customer/topics";
			// Handler sends redirect view ---> D.S
			// resp.sendRedirect(resp.encodeRedirectURL("/customer/topics")
			// WC generates temp redirect resp : SC 302 | location , set-cookie | body :
			// EMPTY
			// web broser sends NEW req : http://host:port/day18/customer/topics --> D.S -->
			// H.M --> null

			// => valid login
			// add validated user details under suitable scope n forward clnt to success.jsp
			return "redirect:/author/success"; // AVN : /WEB-INF/views/user/success.jsp
		} catch (RuntimeException e) {
			System.out.println("err in " + getClass() + " " + e);// NoResExc
			// add err mesg in the model attr map
			map.addAttribute("mesg", "Invalid Login , Please retry.....");// scope : curnt req only
			return "/user/login";// in case of invalid login --> forwarding the clnt to login.jsp , in the SAME
									// req
		}

	}

	// http://localhost:8080/day19_boot_cms/user/logout
	// add a req handling method to forward user to log out page
	@GetMapping("/logout")
	public String logout(HttpSession session,Model map,HttpServletResponse resp,HttpServletRequest request) {
		System.out.println("in logout ");
		//copy user details from session --> request scope(Model map)
		map.addAttribute("user_details", session.getAttribute("user_dtls"));
		//invalidate session
		session.invalidate();
		//set HTTP resp header : refresh , value : dly;url=......
		resp.setHeader("refresh", "5;url="+request.getContextPath()); //  /day19_boot_cms
		return "/user/logout";// AVN : /WEB-INF/views/user/logout.jsp
	}

}
