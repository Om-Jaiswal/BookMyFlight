package com.jaiswal.search.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateConverters {

    @WritingConverter
    public static class DateToStringConverter implements Converter<Date, String> {
        private static final String DATE_FORMAT = "dd-MM-yyyy";

        @Override
        public String convert(Date date) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            return dateFormat.format(date);
        }
    }

    @ReadingConverter
    public static class StringToDateConverter implements Converter<String, Date> {
        private static final String DATE_FORMAT = "dd-MM-yyyy";

        @Override
        public Date convert(String dateString) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            try {
                return dateFormat.parse(dateString);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid date format: " + dateString);
            }
        }
    }
}
