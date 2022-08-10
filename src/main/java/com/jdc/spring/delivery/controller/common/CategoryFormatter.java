package com.jdc.spring.delivery.controller.common;

import com.jdc.spring.delivery.entiity.Category;
import com.jdc.spring.delivery.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class CategoryFormatter implements Formatter<Category> {
	
	@Autowired
	private CategoryRepo repo;

	@Override
	public String print(Category object, Locale locale) {
		
		if(null != object) {
			return String.valueOf(object.getId());
		}
		
		return null;
	}

	@Override
	public Category parse(String text, Locale locale) throws ParseException {
		
		if(null != text && !text.isEmpty()) {
			int id = Integer.parseInt(text);
			return repo.findById(id).orElse(null);
		}
		
		return null;
	}

}
