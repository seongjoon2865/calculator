package com.example.calculator;

/**
 * 사용자 입력을 파싱한 데이터를 저장하는 클래스.
 * 제네릭을 사용하여 다양한 데이터 타입을 지원합니다.
 *
 * @param <T> 첫 번째 숫자의 타입
 * @param <U> 두 번째 숫자의 타입
 */
public class ParsedInput<T, U> {
    private final T firstOperand;  // 첫 번째 피연산자
    private final U secondOperand; // 두 번째 피연산자
    private final Operator operator; // 연산자

    /**
     * ParsedInput 생성자
     *
     * @param firstOperand  첫 번째 피연산자
     * @param secondOperand 두 번째 피연산자
     * @param operator      연산자
     */
    public ParsedInput(T firstOperand, U secondOperand, Operator operator) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.operator = operator;
    }

    /**
     * 첫 번째 피연산자를 반환합니다.
     *
     * @return 첫 번째 피연산자
     */
    public T getFirstOperand() {
        return firstOperand;
    }

    /**
     * 두 번째 피연산자를 반환합니다.
     *
     * @return 두 번째 피연산자
     */
    public U getSecondOperand() {
        return secondOperand;
    }

    /**
     * 연산자를 반환합니다.
     *
     * @return 연산자
     */
    public Operator getOperator() {
        return operator;
    }

    /**
     * ParsedInput 데이터를 출력합니다.
     *
     * @return 입력 데이터의 문자열 표현
     */
    @Override
    public String toString() {
        return "ParsedInput{" +
                "firstOperand=" + firstOperand +
                ", secondOperand=" + secondOperand +
                ", operator=" + operator +
                '}';
    }
}
