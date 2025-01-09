package com.example.calculator;

/**
 * 연산자 정보를 관리하는 열거형
 */
public enum Operator {
    ADD('+'), SUBTRACT('-'), MULTIPLY('*'), DIVIDE('/'), POWER('^');

    private final char symbol;

    Operator(char symbol) {
        this.symbol = symbol;
    }

    /**
     * 기호로부터 연산자를 반환합니다.
     *
     * @param symbol 연산자 기호
     * @return Operator 열거형
     * @throws IllegalArgumentException 유효하지 않은 연산자가 입력된 경우
     */
    public static Operator fromSymbol(char symbol) {
        for (Operator op : values()) {
            if (op.symbol == symbol) {
                return op;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 연산자입니다: " + symbol);
    }

    public char getSymbol() {
        return symbol;
    }
}
