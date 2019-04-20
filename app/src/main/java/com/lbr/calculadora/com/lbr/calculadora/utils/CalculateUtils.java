package com.lbr.calculadora.com.lbr.calculadora.utils;

import android.util.Log;

import com.lbr.calculadora.com.lbr.calculadora.model.Model;

/***
 * @author luan.barbosa.ramalho
 */
public class CalculateUtils {

    public static final int ZERO = 0;
    public static final String MULTIPLICATION = "*";

    public StringBuilder manipulateMultiplicationExpression(StringBuilder expression, String operation){
        int indexOperation = expression.indexOf(operation);

        Model firstNumber = new Model();
        Model secondNumber = new Model();

        //find first number
        for (int i = 1; i <= expression.length(); i++){
            //if index - i minor than zero, then number was found
            if((indexOperation-i) < ZERO){
                firstNumber.setNumber(new Double(firstNumber.getNumberStr().toString()));
                firstNumber.setIndex(ZERO);
                break;
            }

            //If char is a number
            if(String.valueOf(expression.charAt(indexOperation-i)).matches("[0-9] | [\\.]")){
                firstNumber.getNumberStr().insert(ZERO, String.valueOf(expression.charAt(indexOperation-i)));
                firstNumber.setIndex(indexOperation-i);
            }else{
                firstNumber.setNumber(new Double(firstNumber.getNumberStr().toString()));
                break;
            }
        }

        //find second number
        for (int i = 1; i <= expression.length(); i++){
            //if indexOperation + 1 major than String length, then second number was found
            if((indexOperation+i) > expression.length()){
                secondNumber.setNumber(new Double(secondNumber.getNumberStr().toString()));
                secondNumber.setIndex(indexOperation+(i-1));
                break;
            }

            //If char is a number
            if(String.valueOf(expression.charAt(indexOperation+i)).matches("[0-9] | \\.")){
                secondNumber.getNumberStr().append(String.valueOf(expression.charAt(indexOperation+i)));
                secondNumber.setIndex(indexOperation+i);
            }else{
                secondNumber.setNumber(new Double(secondNumber.getNumberStr().toString()));
                break;
            }
        }

        Double result = calculate(operation, firstNumber.getNumber(), secondNumber.getNumber());

        expression.delete(firstNumber.getIndex(), secondNumber.getIndex() == expression.length() ? secondNumber.getIndex() : (secondNumber.getIndex() + 1));
        expression.insert(firstNumber.getIndex(), String.valueOf(result));

        return expression;
    }

    public Double calculate(String operation,Double firstNumber, Double secondNumber) {
        Double result = new Double(0);
        switch (operation){
            case MULTIPLICATION:
                result = firstNumber * secondNumber;
                break;
        }

        return result;
    }


}
