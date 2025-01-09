package com.example.calculator;

import java.util.function.BiFunction;


  //ArithmeticCalculator 클래스는 두 숫자와 연산자를 받아 계산을 수행합니다.

public class ArithmeticCalculator {


      //두 숫자와 연산자를 기반으로 계산 결과를 반환합니다.
      //@param <T> 숫자 타입 (Double, Integer 등)
      //@param num1 첫 번째 숫자
      //@param num2 두 번째 숫자
      //@param operator 연산자
      //@return 계산 결과

    public static <T extends Number> double calculate(T num1, T num2, Operator operator) {
        BiFunction<Double, Double, Double> operation;

        // 연산자에 따른 계산 로직 정의
        switch (operator) {
            case ADD:
                operation = Double::sum;
                break;
            case SUBTRACT:
                operation = (a, b) -> a - b;
                break;
            case MULTIPLY:
                operation = (a, b) -> a * b;
                break;
            case DIVIDE:
                operation = (a, b) -> {
                    if (b == 0) throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
                    return a / b;
                };
                break;
            case POWER:
                operation = Math::pow;
                break;
            default:
                throw new IllegalArgumentException("유효하지 않은 연산자입니다.");
        }

        // 입력값을 Double로 변환하여 계산 수행
        return operation.apply(num1.doubleValue(), num2.doubleValue());
    }
}
