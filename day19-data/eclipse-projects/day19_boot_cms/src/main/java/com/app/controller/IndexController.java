package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	public IndexController() {
		System.out.println("in ctor of " + getClass());
	}

	@GetMapping("/")
	public String showHomePage() {
		return "/index";
	}
}
