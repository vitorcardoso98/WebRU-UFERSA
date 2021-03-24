package com.ufersa.webru.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConversorData implements Converter<String, LocalDate> {

	@Override
	public LocalDate convert(String value) {
		if(value != null) {
		     DateTimeFormatter parseData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return LocalDate.parse(value, parseData);
		}else {
			return null;
		}
	}

}
