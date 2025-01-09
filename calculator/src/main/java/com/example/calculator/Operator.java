package com.example.calculator;

import java.util.Arrays;

/**
 * 연산자에 대한 정보를 관리하는 열거형
 */
public enum Operator {
    ADD('+'), SUBTRACT('-'), MULTIPLY('*'), DIVIDE('/'), POWER('^');

    private final char symbol;

    Operator(char symbol) {
        this.symbol = symbol;
    }

    /**
     * 주어진 기호로부터 연산자 타입을 반환합니다.
     *
     * @param symbol 연산자 기호
     * @return Operator 열거형
     */
    public static Operator fromSymbol(char symbol) {
        return Arrays.stream(values()) // 스트림 사용
                .filter(op -> op.symbol == symbol)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 연산자입니다: " + symbol));
    }

    public char getSymbol() {
        return symbol;
    }
}
