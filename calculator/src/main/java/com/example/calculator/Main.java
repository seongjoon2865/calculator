package com.example.calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("계산기입니다! (종료하려면 '종료'를 입력하세요)");

        while (true) {
            System.out.print("수식을 입력하세요: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("종료")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            try {
                String[] tokens = ExpressionParser.parse(input);

                if (tokens.length != 3) {
                    throw new IllegalArgumentException("수식 형식이 잘못되었습니다. 예: 3 + 5");
                }

                Number operand1 = Double.valueOf(tokens[0]);
                String operatorSymbol = tokens[1];
                Number operand2 = Double.valueOf(tokens[2]);

                Double result = ArithmeticCalculator.calculate(operand1, operand2, operatorSymbol);
                System.out.println("결과: " + result);

            } catch (Exception e) {
                System.out.println("오류: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
