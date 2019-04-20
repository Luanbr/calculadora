package com.lbr.calculadora;

import com.lbr.calculadora.com.lbr.calculadora.utils.CalculateUtils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void  manipulateMultiplicationExpression_test(){
        Boolean f = ".".matches("[0-9] | (\\.)");
        CalculateUtils c = new CalculateUtils();
       StringBuilder t =  c.manipulateMultiplicationExpression(new StringBuilder("2+53*12/10+2-5*3"), "*");
    }
}