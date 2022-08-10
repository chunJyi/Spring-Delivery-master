package com.jdc.spring.delivery.controller;

import com.jdc.spring.delivery.entiity.Item;
import com.jdc.spring.delivery.repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping({"/owner/items", "/items"})
public class ItemsController {
	
	@Autowired
	private ItemRepo repo;

	@GetMapping
	public String home(@RequestParam(defaultValue = "0") Integer cat,
                       ModelMap model, HttpServletRequest req) {
		
		StringBuffer sb = new StringBuffer("select i from Item i where 1 = 1");
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(cat > 0) {
			sb.append(" and i.category.id = :catId");
			params.put("catId", cat);
		}
		
		model.put("items", repo.findByQuery(sb.toString(), params));
		
		if(req.getServletPath().startsWith("/items")) {
			return "/home";
		}
		
		return "/owner/items";
	}
	
	@GetMapping("{id}")
	@ResponseBody
	public Item findById(@PathVariable Integer id) {
		return repo.findById(id).orElse(null);
	}
	
	@PostMapping
	@Secured("ROLE_OWNER")
	public String save(
			@ModelAttribute("item")
			@Valid
			Item item, 
			BindingResult binding) {
		
		if(binding.hasErrors()) {
			return "/owner/items";
		}
		
		repo.save(item);
		
		return "redirect:/owner/items";
	}
	
	@ModelAttribute("item")
	public Item item() {
		return new Item();
	}
}
