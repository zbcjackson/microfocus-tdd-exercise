package com.odde.tdd;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * 3A of UT:
 * Arrange, Act, Assert (AAA)
 */
public class DateUtilsTest {
    @Test
    public void testGetNow(){
        Calendar now = Calendar.getInstance();
        now.set(2019,10,11,2,28,50);
        assertEquals("2019/11/11 14:28:50", DateUtils.format(now));
    }
}
