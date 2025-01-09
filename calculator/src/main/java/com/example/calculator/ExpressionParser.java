package com.example.calculator;

/**
 * 수식을 분석하여 숫자와 연산자를 추출하는 클래스
 */
public class ExpressionParser {

    /**
     * 수식을 파싱하여 ParsedInput 객체로 반환합니다.
     *
     * @param expression 입력된 수식 (예: "12 + 7")
     * @return ParsedInput 객체
     */
    public static ParsedInput parse(String expression) {
        // 수식을 공백 기준으로 분리
        String[] parts = expression.trim().split("\\s+");
        if (parts.length != 3) {
            throw new IllegalArgumentException("잘못된 수식 형식입니다. 예: 12 + 7");
        }

        try {
            // 숫자 및 연산자 추출
            double num1 = Double.parseDouble(parts[0]);
            char operatorSymbol = parts[1].charAt(0);
            double num2 = Double.parseDouble(parts[2]);

            // 연산자 변환
            Operator operator = Operator.fromSymbol(operatorSymbol);

            // ParsedInput 객체 생성
            return new ParsedInput<>(num1, num2, operator);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 올바르게 입력하세요.");
        }
    }
}
