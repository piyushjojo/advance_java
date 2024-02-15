package com.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Mandatory cls level annotation . Tells SC following is a request handling
				// controller(i.e @Controller : cls level annotation) + @ResponseBody : added
				// auto. on ret types of all req handling methods
//annotated by : @ReqMapping / @GetMapping/@PostMapping/@PutMapping....
@RequestMapping("/dummy")
public class DummyController {
	public DummyController() {
		System.out.println("in ctor of " + getClass());
	}
	@GetMapping
	public  List<Integer> getNumberList()
	{
		System.out.println("in get num list");
		return List.of(10,20,30,40,50);
	}
	//Rest Controller ---> rets List<Integer> since ret type is anno with @RespBody ---> D.S -> ser/marshalling
	//java --> json --> sent to REST clnt
}
