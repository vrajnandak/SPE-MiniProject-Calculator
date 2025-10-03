package com.calculator;

import com.calculator.math_lib.MathLib;
import org.junit.Test;
import static org.junit.Assert.*;

public class MathLibTest {
    MathLib mathLib = new MathLib();

    @Test
    public void testSqrt() {
        assertEquals(5.0, mathLib.sqrt(25), 0.0001);
    }
    /*

    @Test
    public void testFactorial() {
        assertEquals(120, mathLib.factorial(5));
    }

    @Test
    public void testLn() {
        assertEquals(0.0, mathLib.ln(1), 0.0001);
    }

    @Test
    public void testPower() {
        assertEquals(8.0, mathLib.power(2, 3), 0.0001);
    }
    */
}

