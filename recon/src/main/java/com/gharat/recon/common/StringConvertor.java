package com.gharat.recon.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class StringConvertor {

    public static final NumericConvertor<Double> toDouble = value -> Double.parseDouble(value);
    public static final NumericConvertor<Integer> toInteger = value -> Integer.parseInt(value);
    public static final DateConvertor<Date> toDate = (value, format) ->
    {
        try {
            return new SimpleDateFormat(format).parse(value);
        } catch (ParseException e) {
            return null;
        }
    };

    @FunctionalInterface
    interface NumericConvertor<T> {
        T convert(String value);
    }

    @FunctionalInterface
    interface DateConvertor<T> {
        T convert(String value, String format);
    }
}
