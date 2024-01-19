package com.kroll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BankExceptionTest {

    @Test
    public void testNegativeInitialDeposit() {
        Bank bank = new Bank();
        assertThrows(NegativeAmountException.class, () -> {
            bank.createAccount("TestAccount", -500);
        });
    }

    @Test
    public void testAccountNotFound() {
        Bank bank = new Bank();
        assertThrows(AccountNotFoundException.class, () -> {
            bank.findAccount(1);
        });
    }

    @Test
    public void testNegativeTransferAmount() {
        Bank bank = new Bank();
        BankAccount account1 = bank.createAccount("Account1", 1000);
        BankAccount account2 = bank.createAccount("Account2", 2000);

        assertThrows(NegativeAmountException.class, () -> {
            bank.transferMoney(account1.getAccountNumber(), account2.getAccountNumber(), -500);
        });
    }
}

