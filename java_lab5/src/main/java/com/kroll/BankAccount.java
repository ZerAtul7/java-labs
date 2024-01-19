package com.kroll;

public class BankAccount {
    private static int nextAccountNumber = 1;
    private int accountNumber;
    private String accountName;
    private double balance;
    public int getAccountNumber() {
        return accountNumber;
    }
    public BankAccount(String accountName, double initialDeposit) {
        if (initialDeposit < 0) {
            throw new NegativeAmountException("Initial deposit cannot be negative");
        }
        this.accountName = accountName;
        this.balance = initialDeposit;
    }
    public void deposit(double amount) {
        if (amount < 0) {
            throw new NegativeAmountException("Сума депозиту не може бути від'ємною");
        }
        balance += amount;
    }
    public void withdraw(double amount) {
        if (amount < 0) {
            throw new NegativeAmountException("Сума виведення не може бути від’ємною");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Недостатньо коштів для виведення");
        }
        balance -= amount;
    }
    public double getBalance() {
        return balance;
    }
    public String getAccountSummary() {
        return "Номер рахунку: " + accountNumber + ", Ім'я: " + accountName + ", Баланс: " + balance;
    }
}
