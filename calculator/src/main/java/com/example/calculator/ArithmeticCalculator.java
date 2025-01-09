package com.example.calculator;

import java.util.function.BiFunction;
import java.util.Map;

/**
 * 사칙연산을 수행하는 클래스
 */
public class ArithmeticCalculator {

    // 연산자를 매핑하는 Map
    private static final Map<Operator, BiFunction<Double, Double, Double>> operations = Map.of(
            Operator.ADD, (a, b) -> a + b,
            Operator.SUBTRACT, (a, b) -> a - b,
            Operator.MULTIPLY, (a, b) -> a * b,
            Operator.DIVIDE, (a, b) -> {
                if (b == 0) {
                    throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
                }
                return a / b;
            },
            Operator.POWER, Math::pow
    );

    /**
     * 두 숫자와 연산자를 기반으로 계산 수행
     *
     * @param num1     첫 번째 숫자
     * @param num2     두 번째 숫자
     * @param operator 연산자
     * @return 계산 결과
     */
    public static double calculate(double num1, double num2, Operator operator) {
        return operations.get(operator).apply(num1, num2);
    }
}
