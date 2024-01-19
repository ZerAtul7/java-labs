package com.kroll;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введіть слово для перевірки на паліндром (або exit для виходу):");
            String word = scanner.nextLine();
            if (word.equalsIgnoreCase("exit")) {
                System.out.println("Програма завершена.");
                break;
            }
            if (PalindromeChecker.isPalindrome(word)) {
                System.out.println(word + " є паліндромом.");
            } else {
                System.out.println(word + " не є паліндромом.");
            }
        }

        scanner.close();
    }
}

