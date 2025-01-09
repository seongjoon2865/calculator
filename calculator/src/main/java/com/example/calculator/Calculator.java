package com.example.calculator;

import java.util.Scanner;

/**
 * 계산기 메인 클래스
 */
public class Calculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("계산기입니다! 종료하려면 'exit'를 입력하세요.");

        while (true) {
            try {
                // 사용자 입력 받기
                System.out.print("첫 번째 숫자를 입력하세요: ");
                double num1 = sc.nextDouble();

                System.out.print("연산자를 입력하세요 (+, -, *, /, ^): ");
                char operatorInput = sc.next().charAt(0);
                Operator operator = Operator.fromSymbol(operatorInput);

                System.out.print("두 번째 숫자를 입력하세요: ");
                double num2 = sc.nextDouble();

                // 계산 수행
                double result = ArithmeticCalculator.calculate(num1, num2, operator);
                System.out.println("결과: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("오류: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("오류가 발생했습니다. 다시 시도하세요.");
                sc.nextLine(); // 입력 스트림 정리
            }

            System.out.print("계속하시겠습니까? ('exit'를 입력하면 종료됩니다): ");
            if (sc.next().equalsIgnoreCase("exit")) {
                break;
            }
        }

        System.out.println("exit");
        sc.close();
    }
}
