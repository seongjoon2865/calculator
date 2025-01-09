package com.example.calculator;

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;
        System.out.println("계산기입니다!");

        do {
            System.out.print("사용하고 Enter를 누르세요 -> ( + , - , * , / , 괄호사용 , 제곱근 , 제곱 : ");
            input = sc.nextLine();

            try {
                double result = evaluateExpression(input);
                System.out.println("결과: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("오류: " + e.getMessage());
            }

            System.out.println("더 계산 하시겠습니까? (종료하려면 '종료'를 입력하세요)");
            input = sc.nextLine();
        } while (!input.equalsIgnoreCase("종료"));

        System.out.println("안녕!");
        sc.close();
    }

    private static double add(double a, double b) { return a + b; }

    private static double subtract(double a, double b) {
        return a - b;
    }

    private static double multiply(double a, double b) {
        return a * b;
    }

    private static double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("0으로 나누는 것은 허용되지 않습니다.");
        }
        return a / b;
    }

    private static double squareRoot(double a) {
        if (a < 0) {
            throw new IllegalArgumentException("음수의 제곱근을 계산할 수 없습니다.");
        }
        return Math.sqrt(a);
    }

    private static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    private static double evaluateExpression(String expression) {
        expression = expression.replaceAll("\\s+", ""); // Remove whitespace

        try {
            if (expression.contains("제곱근")) {
                int start = expression.indexOf("(");
                int end = expression.indexOf(")");
                if (start == -1 || end == -1 || end <= start) {
                    throw new IllegalArgumentException("형식이 잘못되었습니다.");
                }
                double operand = Double.parseDouble(expression.substring(start + 1, end));
                return squareRoot(operand);
            } else if (expression.contains("^")) {
                String[] parts = expression.split("\\^");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("형식이 잘못되었습니다.");
                }
                double base = Double.parseDouble(parts[0]);
                double exponent = Double.parseDouble(parts[1]);
                return power(base, exponent);
            } else {
                return evaluateBasicMath(expression);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("잘못된 표현 형식.");
        }
    }

    private static double evaluateBasicMath(String expression) {
        try {
            String sanitizedExpression = sanitizeExpression(expression);
            return new Object() {
                int pos = -1, ch;

                void nextChar() {
                    ch = (++pos < sanitizedExpression.length()) ? sanitizedExpression.charAt(pos) : -1;
                }

                boolean eat(int charToEat) {
                    while (ch == ' ') nextChar();
                    if (ch == charToEat) {
                        nextChar();
                        return true;
                    }
                    return false;
                }

                double parse() {
                    nextChar();
                    double x = parseExpression();
                    if (pos < sanitizedExpression.length()) throw new RuntimeException("갑작스럽다: " + (char) ch);
                    return x;
                }

                double parseExpression() {
                    double x = parseTerm();
                    for (; ; ) {
                        if (eat('+')) x += parseTerm(); // addition
                        else if (eat('-')) x -= parseTerm(); // subtraction
                        else return x;
                    }
                }

                double parseTerm() {
                    double x = parseFactor();
                    for (; ; ) {
                        if (eat('*')) x *= parseFactor(); // multiplication
                        else if (eat('/')) x /= parseFactor(); // division
                        else return x;
                    }
                }

                double parseFactor() {
                    if (eat('+')) return parseFactor(); // unary plus
                    if (eat('-')) return -parseFactor(); // unary minus

                    double x;
                    int startPos = this.pos;
                    if (eat('(')) { // parentheses
                        x = parseExpression();
                        eat(')');
                    } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                        while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                        x = Double.parseDouble(sanitizedExpression.substring(startPos, this.pos));
                    } else {
                        throw new RuntimeException("갑작스럽다: " + (char) ch);
                    }

                    if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                    return x;
                }
            }.parse();
        } catch (Exception e) {
            throw new IllegalArgumentException("표현오류.");
        }
    }

    private static String sanitizeExpression(String expression) {
        return expression.replaceAll("[^0-9+\\-*/().^]", "");
    }
}
