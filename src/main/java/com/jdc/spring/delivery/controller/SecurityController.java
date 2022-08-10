package com.jdc.spring.delivery.controller;


import com.jdc.spring.delivery.entiity.Account;
import com.jdc.spring.delivery.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {
	
	@Autowired
	private AccountRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/sign-up")
	public String signUp(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
		
		Account account = new Account();
		account.setEnable(true);
		account.setRole(Account.Role.ROLE_CUSTOMER);
		account.setEmail(email);
		account.setPassword(passwordEncoder.encode(password));
		account.setName(name);
		
		repo.save(account);

		return "redirect:/member/home";
	}
}
