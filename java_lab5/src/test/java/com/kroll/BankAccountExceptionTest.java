package com.kroll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BankAccountExceptionTest {

    @Test
    public void testNegativeDeposit() {
        assertThrows(NegativeAmountException.class, () -> {
            new BankAccount("TestAccount", -100);
        });
    }

    @Test
    public void testNegativeWithdrawal() {
        BankAccount account = new BankAccount("TestAccount", 1000);
        assertThrows(NegativeAmountException.class, () -> {
            account.withdraw(-500);
        });
    }

    @Test
    public void testInsufficientFunds() {
        BankAccount account = new BankAccount("TestAccount", 500);
        assertThrows(InsufficientFundsException.class, () -> {
            account.withdraw(1000);
        });
    }
}

