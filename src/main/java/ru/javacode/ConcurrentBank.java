package ru.javacode;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentBank {
    private List<BankAccount> bankAccounts = new ArrayList();

    public BankAccount createAccount(double amount){
        BankAccount bankAccount = new BankAccount(amount);
        bankAccounts.add(bankAccount);
        return bankAccount;
    };

    public double getTotalBalance() {
        return bankAccounts.stream()
                .mapToDouble(BankAccount::getBalance)
                .sum();
    }

    public void transfer(BankAccount from, BankAccount to, int amount) {
        BankAccount lock1 = from.hashCode() < to.hashCode() ? from : to;
        BankAccount lock2 = from.hashCode() > to.hashCode() ? from : to;

        synchronized (lock1) {
            synchronized (lock2) {
                if(amount <= from.getBalance()){
                    from.withdraw(amount);
                    to.deposit(amount);
                }
                else{
                    System.out.println("Insufficient funds");
                }
            }
        }
    }
}
