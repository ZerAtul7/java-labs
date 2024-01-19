package com.kroll;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<BankAccount> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }
    public BankAccount createAccount(String accountName, double initialDeposit) {
        if (initialDeposit < 0) {
            throw new NegativeAmountException("Початковий депозит не може бути негативним");
        }
        BankAccount newAccount = new BankAccount(accountName, initialDeposit);
        accounts.add(newAccount);

        return newAccount;
    }
    public BankAccount findAccount(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        throw new AccountNotFoundException("Рахунок з номером " + accountNumber + " не знайдено");
    }
    public void transferMoney(int fromAccountNumber, int toAccountNumber, double amount) {
        if (amount < 0) {
            throw new NegativeAmountException("Сума переказу не може бути від'ємною");
        }
        BankAccount fromAccount = findAccount(fromAccountNumber);
        BankAccount toAccount = findAccount(toAccountNumber);

        if (fromAccount.getBalance() < amount) {
            throw new InsufficientFundsException("Недостатньо коштів для переказу");
        }
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }
    private int generateAccountNumber() {
        return accounts.size() + 1;
    }
}
