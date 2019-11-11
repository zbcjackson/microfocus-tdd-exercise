package com.odde.tdd;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 3A of UT:
 * Arrange, Act, Assert (AAA)
 */
public class FizzBuzzTest {
    FizzBuzz fb = null;
    @Before
    public void setup(){
        fb = new FizzBuzz();
    }
    @After
    public void tearDown(){
        fb = null;
    }
    @Test
    public void testNormal(){
        assertEquals("1",fb.print(1));
        assertEquals("2",fb.print(2));
    }
    @Test
    public void testFizz(){
        assertEquals("Fizz",fb.print(3));
        assertEquals("Fizz",fb.print(6));
        assertEquals("Fizz",fb.print(31));
    }
    @Test
    public void testBuzz(){
        assertEquals("Buzz",fb.print(5));
        assertEquals("Buzz",fb.print(10));
        assertEquals("Buzz",fb.print(52));
    }

    @Test
    public void testFizzBuzz(){
        assertEquals("FizzBuzz",fb.print(15));
        assertEquals("FizzBuzz",fb.print(30));
        assertEquals("FizzBuzz",fb.print(51));
        assertEquals("FizzBuzz",fb.print(35));
    }
}
