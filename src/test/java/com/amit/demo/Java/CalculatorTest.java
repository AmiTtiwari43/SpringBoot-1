package com.amit.demo.Java;
import com.amit.demo.Java.Calculator ;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    void testAddition(){
        int result = calculator.add(10,20);
        assertEquals(30,result);
    }
    @Test
    void testSubtraction(){
        int result = calculator.subtract(20,10);
        assertEquals(10,result);
    }
    @Test
    void testMultiply(){
        int result = calculator.multiply(5,10);
        assertEquals(50,result);
    }
    @Test
    void testDivision(){
        int result = calculator.divide(25,5);
        assertEquals(5,result);
    }

    @Test
    void testEven(){
        boolean result = calculator.isEven(26);
        assertTrue(result);
    }




}
