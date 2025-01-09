package com.example.calculator;

import java.util.Scanner;

/**
 * 계산기 프로그램
 * 제네릭과 스트림을 활용하여 계산을 처리하는 프로그램입니다.
 */
public class Calculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("계산기입니다!");

        while (true) {
            try {
                // 사용자로부터 첫 번째 숫자 입력
                System.out.print("첫 번째 숫자를 입력하세요: ");
                double num1 = sc.nextDouble();

                // 사용자로부터 연산자 입력
                System.out.print("연산자를 입력하세요 (+, -, *, /, ^): ");
                char operatorInput = sc.next().charAt(0);
                Operator operator = Operator.fromSymbol(operatorInput);

                // 사용자로부터 두 번째 숫자 입력
                System.out.print("두 번째 숫자를 입력하세요: ");
                double num2 = sc.nextDouble();

                // 제네릭을 활용한 계산 수행
                Double result = ArithmeticCalculator.calculate(num1, num2, operator);
                System.out.println("결과: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("오류: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("오류가 발생했습니다. 다시 시도하세요.");
            }

            // 추가 계산 여부 확인
            System.out.println("더 계산하시겠습니까? (종료하려면 'exit'를 입력하세요)");
            String input = sc.next();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
        }

        System.out.println("안녕!");
        sc.close();
    }
}
