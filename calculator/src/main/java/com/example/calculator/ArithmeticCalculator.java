package com.example.calculator;

import java.util.function.BinaryOperator;

/**
 * 사칙연산을 포함한 계산을 수행하는 클래스
 * 제네릭과 스트림을 활용합니다.
 */
public class ArithmeticCalculator {

    /**
     * 제네릭 T를 사용하여 다양한 숫자 타입으로 계산을 수행합니다.
     *
     * @param <T> 숫자 타입 (예: Integer, Double 등)
     * @param num1 첫 번째 숫자
     * @param num2 두 번째 숫자
     * @param operator 연산자
     * @return 계산 결과
     */
    public static <T extends Number> Double calculate(T num1, T num2, Operator operator) {
        double a = num1.doubleValue();
        double b = num2.doubleValue();

        // 스트림을 사용하여 연산을 선택
        BinaryOperator<Double> operation = switch (operator) {
            case ADD -> Double::sum;
            case SUBTRACT -> (x, y) -> x - y;
            case MULTIPLY -> (x, y) -> x * y;
            case DIVIDE -> (x, y) -> {
                if (y == 0) throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
                return x / y;
            };
            case POWER -> Math::pow;
        };

        return operation.apply(a, b);
    }
}
