package com.example.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


 // ExpressionParser 클래스는 수식을 분석하여 숫자와 연산자를 분리하는 역할을 합니다.

public class ExpressionParser {


     //주어진 수식을 숫자와 연산자로 나누는 메서드입니다.

     //@param expression 사용자 입력 수식
     //@return 분리된 숫자와 연산자의 리스트

    public static List<String> parseExpression(String expression) {
        // 정규식을 사용하여 숫자와 연산자를 찾습니다.
        List<String> tokens = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+(?:\\.\\d+)?|[+\\-*/^()]");
        Matcher matcher = pattern.matcher(expression);

        // 정규식에 맞는 부분을 리스트에 추가
        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        return tokens;
    }
}
