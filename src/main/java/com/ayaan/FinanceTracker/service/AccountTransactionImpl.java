package com.ayaan.FinanceTracker.service;

import java.util.Scanner;

import com.ayaan.FinanceTracker.dao.AccountTransactionDAO;
import com.ayaan.FinanceTracker.dao.BankAccountDAO;
import com.ayaan.FinanceTracker.models.AccountTransaction;
import com.ayaan.FinanceTracker.models.BankAccount;

public class AccountTransactionImpl {

    AccountTransactionDAO accountTransactionDAO = new AccountTransactionDAO();
    BankAccountDAO bankAccountDAO = new BankAccountDAO();
    BankAccountImpl bankAccountImpl = new BankAccountImpl();

    public void addTransaction() {
        try {
            System.out.println("Transaction Type");
            Scanner scanner = new Scanner(System.in);
            String type = scanner.nextLine();
            System.out.println("Transaction Amount");
            Double amount = scanner.nextDouble();
            bankAccountImpl.listBankAccount();
            System.out.println("Select your Bank Account by ID ");
            Integer bank = scanner.nextInt();
            BankAccount bankAccount = bankAccountDAO.getBankAccountById(bank);

            if (bankAccount == null) {
                System.out.println("\nError: No Bank account found");
                return;
            }
            AccountTransaction accountTransaction = new AccountTransaction(bankAccount, type, amount);
            accountTransactionDAO.saveTransaction(accountTransaction);
            System.out.println("\nTransaction added successfully");

        } catch (Exception e) {
            System.out.println("\nError: Invalid input");
            e.printStackTrace();
        }
    }
}
