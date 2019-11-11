package com.odde.tdd;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PrimeFactorTest {
    @Test
    public void isPrimeTest(){
        assertTrue(PrimeFactor.isPrime(3));
    }
    @Test
    public void getFactorTest(){
        assertEquals(2, PrimeFactor.isPrime(6));
    }
    @Test
    public void getAllFactorsTest(){
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(2);
        expected.add(2);
        assertTrue(expected.equals(PrimeFactor.getAllFactors(8)));
    }
}
