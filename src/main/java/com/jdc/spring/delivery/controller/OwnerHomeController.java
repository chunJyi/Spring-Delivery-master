package com.jdc.spring.delivery.controller;

import com.jdc.spring.delivery.entiity.Orders;
import com.jdc.spring.delivery.repo.OrdersRepo;
import com.jdc.spring.delivery.pvo.InvoiceListVO;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/owner")
public class OwnerHomeController {
	
	@Autowired
	private OrdersRepo repo;

	@GetMapping("home")
	public String home(
            @RequestParam(defaultValue = "") LocalDate from,
            @RequestParam(defaultValue = "") LocalDate to, ModelMap model) {
		
		StringBuffer sb = new StringBuffer("select o from Orders o where 1 = 1");
		Map<String, Object> params = new HashMap<>();
		
		if(null != from) {
			sb.append(" and o.orderTime >= :from");
			params.put("from", from.atStartOfDay());
		}
		
		if(null != to) {
			sb.append(" and o.orderTime < :to");
			params.put("to", to.atStartOfDay().plusDays(1));
		}
		
		model.put("invoices", repo.findByQuery(sb.toString(), params));
		
		return "/owner/home";
	}
	
	@GetMapping("/invoice/print")
	public String print(
            @RequestParam(defaultValue = "") LocalDate from,
            @RequestParam(defaultValue = "") LocalDate to, ModelMap model) {
		
		StringBuffer sb = new StringBuffer("select o from Orders o where 1 = 1");
		Map<String, Object> params = new HashMap<>();
		
		if(null != from) {
			sb.append(" and o.orderTime >= :from");
			params.put("from", from.atStartOfDay());
		}
		
		if(null != to) {
			sb.append(" and o.orderTime <= :to");
			params.put("to", to.atStartOfDay().plusDays(1));
		}
		
		model.put("DATE_FROM", from);
		model.put("DATE_TO", to);
		model.put("INVOICES", new JRBeanCollectionDataSource(repo.findByQuery(sb.toString(), params)
				.stream().map(InvoiceListVO::new)
				.collect(Collectors.toList())));
		
		return "/jasper/InvoiceList.jasper";
	}

	@GetMapping("invoice/{id:\\d+}")
	public String showDetails(@PathVariable("id") Long id, ModelMap model) {
		
		repo.findById(id).ifPresent(a -> model.put("invoice", a));
		
		return "/member/invoice";
	}
	
	@PostMapping("invoice/{id:\\d+}")
	public String save(@PathVariable("id") Long id,
                       @RequestParam Orders.Status status) {
		
		repo.findById(id).ifPresent(a -> {
			a.setStatus(status);
			repo.save(a);
		});
		
		return "redirect:/owner/home";
	}
}
