package com.actionbazaar.persistence;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate d) {
		return d == null ? null : Date.valueOf(d);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date d) {
		return d == null ? null : d.toLocalDate();
	} 
}
