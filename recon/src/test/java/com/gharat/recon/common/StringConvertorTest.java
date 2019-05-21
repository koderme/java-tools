package com.gharat.recon.common;

import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringConvertorTest {

    @Test
    public void string_to_double_test() {

        double value = 10.12345;

        String valueStr = Double.toString(value);

        double result = StringConvertor.toDouble.convert(valueStr);

        Assert.assertEquals("test", value, result, ReconConstants.Precision.FIFTH);
    }

    @Test
    public void string_to_integer_test() {

        int value = 10;

        String valueStr = Integer.toString(value);

        int result = StringConvertor.toInteger.convert(valueStr);

        Assert.assertEquals("test", value, result);
    }

    @Test
    public void string_to_date_test() {

        String format = "yyyy/MM/dd HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(format);

        Date value = new Date();
        String valueStr = dateFormat.format(value);

        Date result = StringConvertor.toDate.convert(valueStr, format);
        Assert.assertEquals("test", value.toString(), result.toString());
    }

}