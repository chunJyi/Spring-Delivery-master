package com.jdc.spring.delivery.controller.common;

import com.jdc.spring.delivery.entiity.Category;
import com.jdc.spring.delivery.entiity.Orders;
import com.jdc.spring.delivery.entiity.Profile;
import com.jdc.spring.delivery.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@ControllerAdvice
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class CommonDataProvider {

	@Autowired
	private CategoryRepo repo;
	
	@Autowired
	private MyCart cart;
	
	@ModelAttribute("categories")
	public List<Category> categories() {
		return repo.findAll();
	}
	
	@ModelAttribute("genders")
	public Profile.Gender[] genders() {
		return Profile.Gender.values();
	}
	
	@ModelAttribute("statuses")
	public Orders.Status[] statuses() {
		return Orders.Status.values();
	}
	
	@ModelAttribute("itemCount")
	public int itemCount() {
		return cart.itemCount();
	}
}
