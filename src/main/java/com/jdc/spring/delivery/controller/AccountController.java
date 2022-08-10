package com.jdc.spring.delivery.controller;

import com.jdc.spring.delivery.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping({"/owner/members"})
public class AccountController {
	
	@Autowired
	private AccountRepo repo;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping
	public String index(@RequestParam(defaultValue = "") String name,
                        @RequestParam(defaultValue = "false") boolean blocked,
                        ModelMap model) {
		
		StringBuffer sb = new StringBuffer("select a from Account a where 1 = 1");
		Map<String, Object> params = new HashMap<>();
		
		if(!blocked) {
			sb.append(" and a.enable = :enable");
			params.put("enable", true);
		}
		
		if(null != name && !name.isEmpty()) {
			sb.append(" and  (lower(a.name) like lower(:name) or lower(a.email) like lower(:name))");
			params.put("name", name.concat("%"));
		}
		
		model.put("accounts", repo.findByQuery(sb.toString(), params));
		
		return "/owner/customers";
	}
	
	@PostMapping("{id:\\d+}")
	public String burn(@PathVariable("id") Long id) {
		
		repo.findById(id).ifPresent(a -> {
			a.setEnable(!a.isEnable());
			repo.save(a);
		});
		
		return "redirect:/owner/members";
	}
	
	@PostMapping("changepass/{id:\\d+}")
	public String changePassword(@PathVariable("id") Long id,
                                 @RequestParam("password") String password) {
		
		repo.findById(id).ifPresent(a -> {
			a.setPassword(encoder.encode(password));
			repo.save(a);
		});
		
		return "redirect:/owner/members";
	}

}
