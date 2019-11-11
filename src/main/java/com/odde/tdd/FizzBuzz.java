package com.odde.tdd;

public class FizzBuzz {
    public String print (int input){
        String output = Integer.toString(input);
        if (input % 15 == 0 || input % 3 == 0 && output.contains("5") || input % 5 == 0 && output.contains("3")){
            return "FizzBuzz";
        }else if (input % 3 == 0 || output.contains("3")){
            return "Fizz";
        } else if (input % 5 == 0 || output.contains("5")){
            return "Buzz";
        }else {
            return output;
        }
    }
}
