package com.jdc.spring.delivery.controller;

import com.jdc.spring.delivery.entiity.Category;
import com.jdc.spring.delivery.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/owner/categories")
public class CategoryController {
	
	@Autowired
	private CategoryRepo repo;

	@GetMapping
	public String index() {
		return "/owner/categories";
	}
	
	@PostMapping
	public String save(@ModelAttribute @Valid Category category, BindingResult binding) {
		
		if(binding.hasErrors()) {
			return "/owner/categories";
		}
		
		repo.save(category);
		
		return "redirect:/owner/categories";
	}
	
	@PostMapping("/{id}")
	public String save(@PathVariable Integer id, @RequestParam String name) {
		
		repo.findById(id).ifPresent(category -> {
			category.setName(name);
			repo.save(category);
		});
		
		return "redirect:/owner/categories";
	}
	
	@ModelAttribute("category")
	public Category category() {
		return new Category();
	}
}
