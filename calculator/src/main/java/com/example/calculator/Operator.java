package com.example.calculator;


 //연산자 Enum 클래스
 //각 연산자와 이를 나타내는 기호를 관리합니다.

public enum Operator {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('*'),
    DIVIDE('/'),
    POWER('^');

    private final char symbol;

    // 생성자: 연산자 기호를 초기화
    Operator(char symbol) {
        this.symbol = symbol;
    }

    /**
     * //연산자 기호를 가져옵니다.
     *
     * @return 연산자 기호
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * 입력된 기호를 통해 Operator Enum을 반환합니다.
     *
     * @param symbol 입력된 연산자 기호
     * @return 해당하는 Operator Enum
     * @throws IllegalArgumentException 유효하지 않은 기호일 경우 예외 발생
     */
    public static Operator fromSymbol(char symbol) {
        for (Operator op : values()) {
            if (op.symbol == symbol) {
                return op;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 연산자: " + symbol);
    }
}
