package com.amit.demo.Java;

public class Calculator {
    public int add(int a,int b){
        return a+b;
    }
    public int subtract(int a,int b){
        return a -b;
    }
    public int multiply(int a,int b){
        return a*b;
    }
    public int divide(int a,int b){
        return a/b;
    }

    public boolean isEven(int number){
        return (number % 2 == 0) ;
    }
}
