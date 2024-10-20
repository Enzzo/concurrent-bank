package ru.javacode;

import java.math.BigDecimal;

public class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public synchronized void deposit(double amount) {
        this.balance += amount;
    }

    public synchronized void withdraw(double amount) {
        this.balance -= amount;
    }

    public synchronized double getBalance() {
        return this.balance;
    }
}
