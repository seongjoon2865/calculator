package com.example.calculator;

public class ArithmeticCalculator {

    public static Double calculate(Number operand1, Number operand2, String operatorSymbol) {
        Operator operator = Operator.fromSymbol(operatorSymbol);
        return operator.apply(operand1.doubleValue(), operand2.doubleValue());
    }
}
