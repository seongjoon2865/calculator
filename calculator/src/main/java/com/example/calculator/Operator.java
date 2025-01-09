package com.example.calculator;

import java.util.function.BiFunction;

public enum Operator {
    ADD("+", (a, b) -> a + b),
    SUBTRACT("-", (a, b) -> a - b),
    MULTIPLY("*", (a, b) -> a * b),
    DIVIDE("/", (a, b) -> {
        if (b == 0) throw new ArithmeticException("0으로 나눌 수 없습니다.");
        return a / b;
    });

    private final String symbol;
    private final BiFunction<Double, Double, Double> operation;

    Operator(String symbol, BiFunction<Double, Double, Double> operation) {
        this.symbol = symbol;
        this.operation = operation;
    }

    public String getSymbol() {
        return symbol;
    }

    public Double apply(Double a, Double b) {
        return operation.apply(a, b);
    }

    public static Operator fromSymbol(String symbol) {
        for (Operator op : values()) {
            if (op.getSymbol().equals(symbol)) {
                return op;
            }
        }
        throw new UnsupportedOperationException("지원하지 않는 연산자입니다: " + symbol);
    }
}
