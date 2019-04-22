package com.lbr.calculadora;

import com.lbr.calculadora.com.lbr.calculadora.utils.CalculateUtils;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        StringBuilder expression = new StringBuilder("5*6*10*3");
        StringBuilder result = new StringBuilder();
        Pattern pattern = Pattern.compile("\\*");
        Matcher matcher = pattern.matcher(expression.toString());
        CalculateUtils c = new CalculateUtils();
        while (matcher.find()){
            expression = c.manipulateExpression(expression, "*");
        }
        Boolean test = "9".matches("\\*");
//        CalculateUtils c = new CalculateUtils();
       StringBuilder t =  c.manipulateExpression(new StringBuilder("2+53.5*12.3/10+2-5*3"), "*");
       t =  c.manipulateExpression(t, "*");
        t =  c.manipulateExpression(t, "+");
    }
}