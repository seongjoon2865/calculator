package com.example.calculator;

import java.util.function.BiFunction;
import java.util.Map;

/**
 * 사칙연산을 수행하는 클래스
 */
public class ArithmeticCalculator {

    // 연산자를 매핑하는 Map은 키(Key)와 값(Value)의 쌍으로 데이터를 저장하고 관리할 수 있는 자료 구조
    // BiFuntion은 함수형 인터페이스로, 입력 값을 받아서 출력 값을 반환하는 데 사용됩니다.
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

    //apply는 인터페이스에서 사용되는 추상 메서드로, 주어진 입력을 처리한 결과를 반환하는 역할을 합니다.
    public static double calculate(double num1, double num2, Operator operator) {
        return operations.get(operator).apply(num1, num2);
    }
}
