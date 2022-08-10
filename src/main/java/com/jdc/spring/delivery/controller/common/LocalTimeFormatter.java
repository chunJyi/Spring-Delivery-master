package com.jdc.spring.delivery.controller.common;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalTimeFormatter implements Formatter<LocalTime> {

	private DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
	
	@Override
	public String print(LocalTime object, Locale locale) {
		
		if(null  != object) {
			return object.format(df);
		}
		
		return null;
	}

	@Override
	public LocalTime parse(String text, Locale locale) throws ParseException {
		
		if(null != text && !text.isEmpty()) {
			return LocalTime.parse(text, df);
		}
		
		return null;
	}

}
