package com.example.calculator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 수식 파싱 및 계산을 위한 클래스
 */
public class ExpressionParser {

    /**
     * 입력 문자열을 숫자와 연산자로 분리합니다.
     *
     * @param expression 계산식
     * @return 분리된 구성 요소 리스트
     */
    public static List<String> parse(String expression) {
        return Stream.of(expression.split("\\s+")) // 스트림 생성
                .map(String::trim) // 공백 제거
                .collect(Collectors.toList()); // 리스트로 변환
    }
}
