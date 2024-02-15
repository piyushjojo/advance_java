package com.app.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test") // OPTIONAL BUT reco : for functional separation
public class TestController {
	public TestController() {
		System.out.println("in ctor of " + getClass());
	}
	//add req handling method to generate dyn resp using model attrs : ModelAndView class
	@GetMapping("/test1")
	public ModelAndView testModelAndView()
	{
		System.out.println("in test m n v");
		//API ModelAndView(String lvn,String modelAttrName,Object modelAttrVal)
		return new ModelAndView("/test/test1", "server_ts", LocalDateTime.now());
		//AVN : /WEB-INF/views/test/test1.jsp
	}
	//add req handling method to generate dyn resp using model attrs : via Model map
	@GetMapping("/test2")
	public String testModelMap(Model map) //D.I
	{
		System.out.println("in test model map "+map);//{}
		//Model API : public Model addAttribute(String attrName,Object attrValue)
		map.addAttribute("server_ts",new Date())
		.addAttribute("num_list", Arrays.asList(10,20,30,40));
		System.out.println("map again "+map);//{....}
		return "/test/test1";//AVN : /WEB-INF/views/test/test1.jsp
		//SC will implicitly ret populated model map along with LVN
		
	}
}
