package com.odde.tdd;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactor {
    public static List<Integer> getAllFactors(int input){
        List<Integer> factors = new ArrayList<Integer>();
        int result=2;
        while(result != input) {
            result = getFactor(input);
            if (isPrime(result)) {
                factors.add(result);
                input = input/result;
            }
        }
        factors.add(input);
        return factors;
    }
    public static int getFactor(int input) {
        for (int i=2;i<input;i++) {
            if (input % i == 0) {
                return i;
            }else{
                continue;
            }
        }
        return input;
    }
    public static boolean isPrime(int input){
        return getFactor(input) == input;
    }
}
