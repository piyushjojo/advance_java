package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dao.ITopicDao;
import com.app.service.ITutorialService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	//dep : topic dao i/f
	@Autowired
	private ITopicDao topicDao;
	//dep : tut service i/f
	@Autowired
	private ITutorialService tutService;
	
	public CustomerController() {
		System.out.println("in ctor of " + getClass());
	}
	//add rewq handling method to forward the cltn to topics.jsp
	@GetMapping("/topics")
	public String showTopics(Model map)
	{
		System.out.println("in show topics "+map);
		System.out.println("dao "+topicDao.getClass());
		map.addAttribute("topic_list", topicDao.getAllTopics());
		return "/customer/topics";//AVN : /WEB-INF/views/customer/topics.jsp
	}
	//Hint : http://localhost:8080/day18_boot_cms/customer/tutorials?topicId=2
	//add req handling method to forward clnt to tutorials page 
	@GetMapping("/tutorials")
	public String showTutorialTitles(Model map,@RequestParam long topicId)
	{
		System.out.println("in show tut titles");
		map.addAttribute("tut_names", tutService.getTutorialNamesByTopic(topicId));
		return "/customer/tutorials";//AVN : /WEB-INF/views/customer/tutorials.jsp
	}
}
