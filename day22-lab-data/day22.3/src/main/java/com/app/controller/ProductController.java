package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
	// any one should be able view the products
	@GetMapping("/view")
	public String viewProducts() {
		return "any one can view the products!!!!!!!!!";
	}

	// customer should be able to purchase products
	@GetMapping("/purchase")
	public String purchaseProducts() {
		return "customer should be able to purchase products";
	}

	// admin should be able to add the products
	@GetMapping("/add")
	public String addProducts() {
		return "admin should be able to add the products";
	}

}
