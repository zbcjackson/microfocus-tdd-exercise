package com.odde.tdd;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
    public static String getNow(){
        return format(now());
    }
    private static Calendar now(){
        return Calendar.getInstance();
    }
    public static String format(Calendar now) {
     return new SimpleDateFormat("yyyy/MM/dd hh/mm/ss").format(now);
    }
}
