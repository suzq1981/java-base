package com.djt.mvc2.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DateConvert implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		if (source != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return format.parse(source);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
