package com.jdc.spring.delivery.controller.common;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

	private DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	@Override
	public String print(LocalDateTime object, Locale locale) {
		
		if(null  != object) {
			return object.format(df);
		}
		
		return null;
	}

	@Override
	public LocalDateTime parse(String text, Locale locale) throws ParseException {
		
		if(null != text && !text.isEmpty()) {
			return LocalDateTime.parse(text, df);
		}
		
		return null;
	}

}
