package com.example.calculator;

import java.util.Scanner;


 // 계산기 프로그램
 // 사용자가 입력한 수식(사칙연산, 괄호, 제곱근 등)을 계산하는 프로그램입니다.

public class Calculator {

    public static void main(String[] args) {
        // 사용자로부터 입력을 받기 위해 Scanner 객체 생성
        Scanner sc = new Scanner(System.in);
        String input;
        System.out.println("계산기입니다!"); // 프로그램 시작 메시지

        do {
            // 사용자에게 수식을 입력받음
            System.out.print("사용하고 Enter를 누르세요 -> ( + , - , * , / , 괄호사용 , 제곱근 , 제곱 : ");
            input = sc.nextLine();

            try {
                // 수식을 계산하고 결과를 출력
                double result = evaluateExpression(input);
                System.out.println("결과: " + result);
            } catch (IllegalArgumentException e) {
                // 수식에 문제가 있을 경우 오류 메시지 출력
                System.out.println("오류: " + e.getMessage());
            }

            // 추가 계산 여부 확인
            System.out.println("더 계산 하시겠습니까? (종료하려면 '종료'를 입력하세요)");
            input = sc.nextLine();
        } while (!input.equalsIgnoreCase("종료")); // "종료"를 입력하면 반복 종료

        System.out.println("안녕!"); // 프로그램 종료 메시지
        sc.close(); // Scanner 자원 해제
    }

    // 두 수를 더하는 메서드
    private static double add(double a, double b) {
        return a + b;
    }

    // 두 수를 빼는 메서드
    private static double subtract(double a, double b) {
        return a - b;
    }

    // 두 수를 곱하는 메서드
    private static double multiply(double a, double b) {
        return a * b;
    }

    // 두 수를 나누는 메서드 (0으로 나누면 예외 발생)
    private static double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("0으로 나누는 것은 허용되지 않습니다.");
        }
        return a / b;
    }

    // 숫자의 제곱근을 구하는 메서드 (음수는 예외 발생)
    private static double squareRoot(double a) {
        if (a < 0) {
            throw new IllegalArgumentException("음수의 제곱근을 계산할 수 없습니다.");
        }
        return Math.sqrt(a);
    }

    // 숫자의 제곱을 구하는 메서드
    private static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    // 사용자 입력 수식을 분석하고 결과를 계산하는 메서드
    private static double evaluateExpression(String expression) {
        expression = expression.replaceAll("\\s+", ""); // 공백 제거

        try {
            if (expression.contains("제곱근")) { // 제곱근 계산
                int start = expression.indexOf("(");
                int end = expression.indexOf(")");
                if (start == -1 || end == -1 || end <= start) {
                    throw new IllegalArgumentException("형식이 잘못되었습니다.");
                }
                double operand = Double.parseDouble(expression.substring(start + 1, end));
                return squareRoot(operand);
            } else if (expression.contains("^")) { // 제곱 계산
                String[] parts = expression.split("\\^");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("형식이 잘못되었습니다.");
                }
                double base = Double.parseDouble(parts[0]);
                double exponent = Double.parseDouble(parts[1]);
                return power(base, exponent);
            } else {
                return evaluateBasicMath(expression); // 일반 사칙연산 계산
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("잘못된 표현 형식.");
        }
    }

    // 기본 사칙연산을 처리하는 메서드
    private static double evaluateBasicMath(String expression) {
        try {
            String sanitizedExpression = sanitizeExpression(expression); // 수식 정리
            return new Object() {
                int pos = -1, ch; // 현재 위치와 문자를 나타내는 변수

                // 다음 문자를 읽어오는 메서드
                void nextChar() {
                    ch = (++pos < sanitizedExpression.length()) ? sanitizedExpression.charAt(pos) : -1;
                }

                // 특정 문자를 처리하는 메서드
                boolean eat(int charToEat) {
                    while (ch == ' ') nextChar();
                    if (ch == charToEat) {
                        nextChar();
                        return true;
                    }
                    return false;
                }

                // 전체 수식을 파싱하는 메서드
                double parse() {
                    nextChar();
                    double x = parseExpression();
                    if (pos < sanitizedExpression.length()) throw new RuntimeException("갑작스럽다: " + (char) ch);
                    return x;
                }

                // 표현식을 처리하는 메서드 (덧셈, 뺄셈)
                double parseExpression() {
                    double x = parseTerm();
                    for (; ; ) {
                        if (eat('+')) x += parseTerm(); // 덧셈
                        else if (eat('-')) x -= parseTerm(); // 뺄셈
                        else return x;
                    }
                }

                // 항(term)을 처리하는 메서드 (곱셈, 나눗셈)
                double parseTerm() {
                    double x = parseFactor();
                    for (; ; ) {
                        if (eat('*')) x *= parseFactor(); // 곱셈
                        else if (eat('/')) x /= parseFactor(); // 나눗셈
                        else return x;
                    }
                }

                // 숫자 또는 괄호를 처리하는 메서드
                double parseFactor() {
                    if (eat('+')) return parseFactor(); // 단항 덧셈
                    if (eat('-')) return -parseFactor(); // 단항 뺄셈

                    double x;
                    int startPos = this.pos;
                    if (eat('(')) { // 괄호 처리
                        x = parseExpression();
                        eat(')');
                    } else if ((ch >= '0' && ch <= '9') || ch == '.') { // 숫자 처리
                        while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                        x = Double.parseDouble(sanitizedExpression.substring(startPos, this.pos));
                    } else {
                        throw new RuntimeException("갑작스럽다: " + (char) ch);
                    }

                    if (eat('^')) x = Math.pow(x, parseFactor()); // 거듭제곱

                    return x;
                }
            }.parse();
        } catch (Exception e) {
            throw new IllegalArgumentException("표현오류.");
        }
    }

    // 수식을 정리하여 허용된 문자만 남기는 메서드
    private static String sanitizeExpression(String expression) {
        return expression.replaceAll("[^0-9+\\-*/().^]", "");
    }
}
