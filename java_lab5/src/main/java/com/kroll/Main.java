package com.kroll;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Створити акаунт");
            System.out.println("2. Депозит коштів");
            System.out.println("3. Виведення коштів");
            System.out.println("4. Переказ");
            System.out.println("5. Отримати загальний рахунок");
            System.out.println("6. Вийти");
            System.out.print("Введіть свій вибір: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount(bank, scanner);
                    break;
                case 2:
                    deposit(bank, scanner);
                    break;
                case 3:
                    withdraw(bank, scanner);
                    break;
                case 4:
                    transferMoney(bank, scanner);
                    break;
                case 5:
                    getAccountSummary(bank, scanner);
                    break;
                case 6:
                    System.out.println("Вихід з програми. До побачення!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Невірний вибір. Введіть правильний варіант.");
            }
        }
    }
    private static void createAccount(Bank bank, Scanner scanner) {
        System.out.print("Введіть назву облікового запису: ");
        String accountName = scanner.next();
        System.out.print("Введіть початкову суму депозиту: ");
        double initialDeposit = scanner.nextDouble();

        try {
            BankAccount account = bank.createAccount(accountName, initialDeposit);
            System.out.println("Акаунт " + account.getAccountNumber() + " створено успішно.");
        } catch (NegativeAmountException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
    private static void deposit(Bank bank, Scanner scanner) {
        System.out.print("Введіть номер рахунку (accountNumber): ");
        int accountNumber = scanner.nextInt();
        System.out.print("Введіть суму депозиту: ");
        double amount = scanner.nextDouble();

        try {
            BankAccount account = bank.findAccount(accountNumber);
            account.deposit(amount);
            System.out.println("Депозит пройшов успішно. Оновлений баланс: " + account.getBalance());
        } catch (NegativeAmountException | AccountNotFoundException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
    private static void withdraw(Bank bank, Scanner scanner) {
        System.out.print("Введіть номер рахунку (accountNumber): ");
        int accountNumber = scanner.nextInt();
        System.out.print("Введіть суму зняття: ");
        double amount = scanner.nextDouble();

        try {
            BankAccount account = bank.findAccount(accountNumber);
            account.withdraw(amount);
            System.out.println("Виведення пройшло успішно. Оновлений баланс: " + account.getBalance());
        } catch (NegativeAmountException | InsufficientFundsException | AccountNotFoundException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
    private static void transferMoney(Bank bank, Scanner scanner) {
        System.out.print("Введіть вихідний номер рахунку: ");
        int fromAccountNumber = scanner.nextInt();
        System.out.print("Введіть номер рахунку призначення: ");
        int toAccountNumber = scanner.nextInt();
        System.out.print("Введіть суму переказу: ");
        double amount = scanner.nextDouble();

        try {
            bank.transferMoney(fromAccountNumber, toAccountNumber, amount);
            System.out.println("Передача успішна.");
        } catch (NegativeAmountException | InsufficientFundsException | AccountNotFoundException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
    private static void getAccountSummary(Bank bank, Scanner scanner) {
        System.out.print("Введіть номер рахунку (accountNumber): ");
        int accountNumber = scanner.nextInt();

        try {
            BankAccount account = bank.findAccount(accountNumber);
            System.out.println(account.getAccountSummary());
        } catch (AccountNotFoundException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
