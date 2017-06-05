package com.revature.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by mnikitin on 5/11/17.
 */
@Converter
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime ldt) {
		if(ldt == null) return null;
		return Timestamp.valueOf(ldt);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
		if(timestamp == null) return null;
		return timestamp.toLocalDateTime();
	}
}
