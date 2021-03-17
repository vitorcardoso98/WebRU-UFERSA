package com.ufersa.webru.converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConversorHora implements Converter<String, LocalTime> {

	@Override
	public LocalTime convert(String value) {
		if(value != null) {
		DateTimeFormatter parserHora= DateTimeFormatter.ofPattern("HH:mm");
		return LocalTime.parse(value, parserHora);
		}else {
			return null;
		}
	}
}
