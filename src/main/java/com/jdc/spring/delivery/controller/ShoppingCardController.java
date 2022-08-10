package com.jdc.spring.delivery.controller;

import com.jdc.spring.delivery.entiity.*;
import com.jdc.spring.delivery.repo.AccountRepo;
import com.jdc.spring.delivery.repo.OrdersRepo;
import com.jdc.spring.delivery.controller.common.MyCart;
import com.jdc.spring.delivery.repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
@RequestMapping({"/cart", "/member/cart"})
@Scope(WebApplicationContext.SCOPE_SESSION)
public class
ShoppingCardController implements MyCart {

	private Orders invoice;
	
	@Autowired
	private ItemRepo itemRepo;
	@Autowired
	private AccountRepo accRepo;
	@Autowired
	private OrdersRepo odRepo;
	
	@PostConstruct
	private void init() {
		invoice = new Orders();
	}
	
	@PostMapping
	@Secured("ROLE_CUSTOMER")
	public String checkOut(
			@RequestParam LocalDate date,
			@RequestParam LocalTime timeFrom,
			@RequestParam LocalTime timeTo,
			@RequestParam String phone,
			@RequestParam String division,
			@RequestParam String township,
			@RequestParam String address
			) {
		
		invoice.setOrderTime(LocalDateTime.now());
		invoice.setDesireDate(date);
		invoice.setTimeFrom(timeFrom);
		invoice.setTimeTo(timeTo);
		invoice.setPhone(phone);
		
		Address ad = new Address();
		ad.setDivision(division);
		ad.setTownship(township);
		ad.setAddress(address);
		
		invoice.setAddress(ad);
		
		odRepo.save(invoice);
		
		long id = invoice.getId();
		invoice = new Orders();
		
		return "redirect:/member/invoice/" + id;
	}
	
	@GetMapping
	@Secured("ROLE_CUSTOMER")
	public String index(ModelMap model, Principal principal) {
		
		if(null != principal) {
			String email = principal.getName();
			Account loginUser = accRepo.findByEmail(email);
			invoice.setCustomer(loginUser);
			
			Profile profile = loginUser.getProfile();
			if(null != profile) {
				invoice.setAddress(profile.getAddress());
			}
		}
		
		model.put("invoice", invoice);
		return "/member/my-cart";
	}

	@ResponseBody
	@GetMapping("/add/{id:\\d+}")
	public int addToCart(@PathVariable int id) {
		
		Item item = itemRepo.findById(id).orElse(null);
		
		if(null != item) {
			invoice.addItem(item);
		}
		
		return itemCount();
	}

	@Override
	public int itemCount() {
		return invoice.getOrders().stream().mapToInt(a -> a.getQuentity()).sum();
	}
}
