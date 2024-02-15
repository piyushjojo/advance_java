package com.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.dao.ITopicDao;
import com.app.pojos.Topic;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	// dependency : DAO layer i/f
	@Autowired
	private ITopicDao topicDao;

	@PostConstruct
	public void init() {
		System.out.println("in init of " + getClass().getName() + " " + topicDao);
	}

	// add request handling method to get all topics n share it with the view layer
	@GetMapping("/topics")
	public String showAllTopics(Model map) {
		System.out.println("in show all topics");
		// invoke dao's method to get all topics
		map.addAttribute("topic_list", topicDao.getAllTopics());
		return "/customer/topics";
	}

}
