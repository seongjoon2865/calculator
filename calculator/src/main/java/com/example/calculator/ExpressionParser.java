package com.example.calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExpressionParser {
    public static String[] parse(String expression) {
        String regex = "([-+]?\\d*\\.?\\d+|[-+/*])"; // 숫자 또는 연산자 추출
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression.replaceAll("\\s+", "")); // 공백 제거

        // MatchResult를 사용하여 group() 호출
        return matcher.results()
                .map(match -> match.group())
                .toArray(String[]::new);
    }
}
