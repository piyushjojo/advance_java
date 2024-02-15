package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.pojos.User;
import com.app.pojos.UserRole;
import com.app.service.IUserService;

@Controller // mandatory
@RequestMapping("/user") // optional
public class UserController {
	// dependency : service layer i/f
	@Autowired
	private IUserService userService;

	public UserController() {
		System.out.println("in ctor of " + getClass().getName());
	}

	// add req handling method (to service GET) : to show login form
	@GetMapping("/login")
	// In HandlerMapping bean :
	// key : /user/login+method=GET
	// value : com.app.controller.UserController.showLoginForm
	public String showLoginForm() {
		System.out.println("in show login form : user service  "+userService.getClass());
		return "/user/login";// Handler(UserController) --> LVN --> D.S
		// V.R : AVN : /WEB-INF/views/user/login.jsp
	}

	// add req handling method(to service POST) : to process the form
	@PostMapping("/login")
	// In HandlerMapping bean :
	// key : /user/login+method=POST
	// value : com.app.controller.UserController.processLoginForm
	// Which req params will be sent from the clnt ? email n pwd
	public String processLoginForm(@RequestParam String email, 
			@RequestParam(name = "password") String pwd, Model map,HttpSession hs)
	// SC : String email=request.getParameter("email");
	// String pwd=request.getParameter("password");
	// RECO : Match req param names with method arg names!
	{
		System.out.println("in process login form " + email + " " + pwd);
		try {
			// invoke service layer method for user validation
			User user = userService.validateUser(email, pwd);
			// => valid login
			//add validated user details under model map , as model attribute --so that D.S will store it auto 
			//under request scope.
			hs.setAttribute("user_details", user);
			// role checking
			if (user.getRole().equals(UserRole.ADMIN))
				return "/admin/add_tut_form";//AVN : /WEB-INF/views/admin/add_tut_form.jsp
		//	return "/customer/topics";// LVN : /customer/topics
			return "redirect:/customer/topics";
			// AVN : /WEB-INF/views/customer/topics.jsp

		} catch (RuntimeException e) {
			System.out.println("err in user controller " + e);
			map.addAttribute("message", "Invalid Login , Please retry !!!!!!");
			return "/user/login";// Actual View Name(AVN) : /WEB-INF/views/user/login.jsp
		}

	}
}
