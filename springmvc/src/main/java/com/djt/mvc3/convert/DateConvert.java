package com.djt.mvc3.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConvert implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		SimpleDateFormat format = null;
		if (source != null) {
			format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return format.parse(source);
			} catch (ParseException e) {
				format = new SimpleDateFormat("yyyy/MM/dd");
				try {
					return format.parse(source);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}

}
