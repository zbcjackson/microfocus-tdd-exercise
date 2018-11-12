package com.odde.tdd;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    @Test
    public void one_add_one(){
        Calculator calculator = new Calculator();
        int result = calculator.add(1, 1);
        assertEquals(2, result);
    }
}
