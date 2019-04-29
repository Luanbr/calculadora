package com.lbr.calculadora.com.lbr.calculadora.utils;

import com.lbr.calculadora.com.lbr.calculadora.model.Model;

/***
 * @author luan.barbosa.ramalho
 */
public class CalculateUtils {

    public static final int ZERO = 0;
    public static final String MULTIPLICATION = "*";
    public static final String DIVISION = "/";
    public static final String ADDITION = "+";
    public static final String SUBTRACTION = "-";
    public static final String POWER = "^";
    public static final String COSINE = "cos";
    public static final String TANGENT = "tan";
    public static final String SINE = "sin";
    public static final String SQRT = "√";

    public StringBuilder manipulateExpression(StringBuilder expression, String operation){

        Model firstNumber = findFirstNumber(expression, operation);
        Model secondNumber = findSecondNumber(expression, operation);

        Double result = calculate(operation, firstNumber.getNumber(), secondNumber.getNumber());

        expression.delete(firstNumber.getIndex(), secondNumber.getIndex() == expression.length() ? secondNumber.getIndex() : (secondNumber.getIndex() + 1));
        expression.insert(firstNumber.getIndex(), String.valueOf(result));

        return expression;
    }

    public StringBuilder manipulateExpressionOperationBeforeNumber(StringBuilder expression, String operation){

        Model number = findNumOperationBeforeNumber(expression, operation);

        Double result = calculate(operation, number.getNumber(), null);

        expression.delete(number.getIndexOperation(), number.getIndex() == expression.length() ? number.getIndex() : (number.getIndex() + 1));
        expression.insert(number.getIndexOperation(), String.valueOf(result));

        return expression;
    }

    public static Model findFirstNumber(StringBuilder expression, String operation) {
        int indexOperation = expression.indexOf(operation);
        int lastIndexOperation = indexOperation + operation.length();
        Model firstNumber = new Model();

        //find first number
        for (int i = 1; i <= expression.length(); i++){
            //if index - i minor than zero, then number was found
            if((indexOperation-i) < ZERO){
                firstNumber.setNumber(new Double(firstNumber.getNumberStr().toString()));
                firstNumber.setIndex(ZERO);
                break;
            }

            //If char is a number
            if(String.valueOf(expression.charAt(indexOperation-i)).matches("[0-9\\.]")){
                firstNumber.getNumberStr().insert(ZERO, String.valueOf(expression.charAt(indexOperation-i)));
                firstNumber.setIndex(indexOperation-i);
            }else{
                firstNumber.setNumber(new Double(firstNumber.getNumberStr().toString()));
                break;
            }
        }

        firstNumber.setIndexOperation(indexOperation);
        firstNumber.setLastIndexOperation(lastIndexOperation);

        return firstNumber;
    }

    public static Model findSecondNumber(StringBuilder expression, String operation) {
        int indexOperation = expression.indexOf(operation);
        Model secondNumber = new Model();

        //find second number
        for (int i = 1; i <= expression.length(); i++){
            //if indexOperation + 1 major than String length, then second number was found
            if((indexOperation+i) >= expression.length()){
                secondNumber.setNumber(new Double(secondNumber.getNumberStr().toString()));
                secondNumber.setIndex(indexOperation+(i-1));
                break;
            }

            //If char is a number
            if(String.valueOf(expression.charAt(indexOperation+i)).matches("[0-9\\.]")){
                secondNumber.getNumberStr().append(String.valueOf(expression.charAt(indexOperation+i)));
                secondNumber.setIndex(indexOperation+i);
            }else{
                secondNumber.setNumber(new Double(secondNumber.getNumberStr().toString()));
                break;
            }
        }
        secondNumber.setIndexOperation(indexOperation);

        return secondNumber;
    }

    public static Model findNumOperationBeforeNumber(StringBuilder expression, String operation) {
        int indexOperation = expression.indexOf(operation);
//        int lastIndexOperation = indexOperation + operation.length();
        int lastIndexOperation = indexOperation + operation.length() - 1;

        Model number = new Model();

        //find  number
        for (int i = 1; i <= expression.length(); i++){
            //if lastIndexOperation + 1 major than String length, then number was found
            if((lastIndexOperation+i) >= expression.length()){
                number.setNumber(new Double(number.getNumberStr().toString()));
                number.setIndex(lastIndexOperation+(i-1));
                break;
            }

            //If char is a number
            if(String.valueOf(expression.charAt(lastIndexOperation+i)).matches("[0-9\\.]")){
                number.getNumberStr().append(String.valueOf(expression.charAt(lastIndexOperation+i)));
                number.setIndex(lastIndexOperation+i);
            }else{
                number.setNumber(new Double(number.getNumberStr().toString()));
                break;
            }
        }
        number.setIndexOperation(indexOperation);
        number.setLastIndexOperation(lastIndexOperation);

        return number;
    }

    public Double calculate(String operation,Double firstNumber, Double secondNumber) {
        Double result = new Double(0);
        switch (operation){
            case MULTIPLICATION:
                result = firstNumber * secondNumber;
                break;
            case DIVISION:
                result = firstNumber / secondNumber;
                break;
            case ADDITION:
                result = firstNumber + secondNumber;
                break;
            case SUBTRACTION:
                result = firstNumber - secondNumber;
                break;
            case POWER:
                result = Math.pow(firstNumber, secondNumber);
                break;
            case COSINE:
                result = Math.cos(Math.toRadians(firstNumber));
                break;
            case SINE:
                result = Math.sin(Math.toRadians(firstNumber));
                break;
            case TANGENT:
                result = Math.tan(Math.toRadians(firstNumber));
                break;
        }

        return result;
    }
}
