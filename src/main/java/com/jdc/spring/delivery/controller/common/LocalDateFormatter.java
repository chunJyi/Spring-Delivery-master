package com.jdc.spring.delivery.controller.common;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateFormatter implements Formatter<LocalDate> {

	private DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	@Override
	public String print(LocalDate object, Locale locale) {
		
		if(null  != object) {
			return object.format(df);
		}
		
		return null;
	}

	@Override
	public LocalDate parse(String text, Locale locale) throws ParseException {
		
		if(null != text && !text.isEmpty()) {
			return LocalDate.parse(text, df);
		}
		
		return null;
	}

}
