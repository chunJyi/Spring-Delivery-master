package com.jdc.spring.delivery.controller;

import com.jdc.spring.delivery.entiity.Account;
import com.jdc.spring.delivery.entiity.Orders;
import com.jdc.spring.delivery.entiity.Profile;
import com.jdc.spring.delivery.repo.AccountRepo;
import com.jdc.spring.delivery.repo.OrdersRepo;
import com.jdc.spring.delivery.repo.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class CustomerHomeController {
	
	@Autowired
	private ProfileRepo profileRepo;
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private OrdersRepo invoiceRepo;

	@Autowired
	private OrdersRepo repo;

//	@GetMapping("search")
//	public String search(
//			@RequestParam(defaultValue = "") LocalDate from,
//			@RequestParam(defaultValue = "") LocalDate to, ModelMap model) {
//
//		StringBuffer sb = new StringBuffer("select o from Orders o where 1 = 1");
//		Map<String, Object> params = new HashMap<>();
//
//		if(null != from) {
//			sb.append(" and o.orderTime >= :from");
//			params.put("from", from.atStartOfDay());
//		}
//
//		if(null != to) {
//			sb.append(" and o.orderTime < :to");
//			params.put("to", to.atStartOfDay().plusDays(1));
//		}
//
//		model.put("invoices", repo.findByQuery(sb.toString(), params));
//
//		return "/member/home";
//	}

	@GetMapping("/home")
	public String home(ModelMap map, HttpServletRequest request) {
		
		String loginId = request.getRemoteUser();
		
		// profile
		Profile profile = profileRepo.findByAccountEmail(loginId);
		
		if(null == profile) {
			profile = new Profile();
			profile.setAccount(accountRepo.findByEmail(loginId));
			map.put("profile", profile);
			
			return "/member/profile-edit";
		}
		
		// invoice history
		List<Orders> history = invoiceRepo.findByCustomerEmail(loginId);
		
		map.put("profile", profile);
		map.put("history", history);
		
		return "/member/home";
	}
	
	@PostMapping("/profile")
	public String updateProfile(@ModelAttribute("profile") @Valid Profile profile, BindingResult binding) {
		
		if(binding.hasErrors()) {
			return "/member/profile-edit";
		}
		
		Account account = accountRepo.getOne(profile.getId());
		account.setName(profile.getAccount().getName());
		
		profile.setAccount(account);
		accountRepo.save(account);
		
		profileRepo.save(profile);
		
		return "redirect:/member/home";
	}
	
	@GetMapping("/invoice/{id:\\d+}")
	public String invoice(@PathVariable Long id, ModelMap model) {
		
		model.put("invoice", invoiceRepo.findById(id).orElse(null));
		return "/member/invoice";
	}
	
	@PostMapping("/invoice/{id:\\d+}/{status}")
	public String changeStatus(@PathVariable Long id, @PathVariable Orders.Status status) {
		
		Orders invoice = invoiceRepo.findById(id).orElse(null);
		
		if(null != invoice) {
			invoice.setStatus(status);
			invoiceRepo.save(invoice);
		}
		
		return "redirect:/member/invoice/" + id;
	}
	
	@GetMapping("/invoice/print/{id:\\d+}")
	public String print(@PathVariable Long id, ModelMap model) {
		Orders invoice = invoiceRepo.findById(id).orElse(null);
		model.put("INVOICE", invoice);
		return "/jasper/Invoice.jasper";
	}
	
	@ModelAttribute("profile")
	public Profile profile() {
		return new Profile();
	}
}
