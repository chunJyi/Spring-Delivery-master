package com.jdc.spring.delivery.controller;

import com.jdc.spring.delivery.repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class DeliveryHomeController {
	
	@Autowired
	private ItemRepo repo;
	
	@GetMapping
	public String index(ModelMap model) {
		model.put("items", repo.findAll());
		return "home";
	}
	
	@GetMapping("{cat:\\d+}")
	public String index(@PathVariable Integer cat, ModelMap model) {
		model.put("items", repo.findByCategoryId(cat));
		return "home";
	}

}
