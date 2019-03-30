package com.actionbazaar.persistence;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime d) {
		return d == null ? null : Timestamp.valueOf(d);
	}
	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp d) {
		return d == null ? null : d.toLocalDateTime();
	}
}
