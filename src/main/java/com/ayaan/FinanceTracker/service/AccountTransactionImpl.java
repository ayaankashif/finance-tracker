package com.ayaan.FinanceTracker.service;

import java.util.Scanner;

import com.ayaan.FinanceTracker.dao.AccountTransactionDAO;
import com.ayaan.FinanceTracker.dao.BankAccountDAO;
import com.ayaan.FinanceTracker.models.AccountTransaction;
import com.ayaan.FinanceTracker.models.BankAccount;

public class AccountTransactionImpl {

    AccountTransactionDAO accountTransactionDAO = new AccountTransactionDAO();
    BankAccountDAO bankAccountDAO = new BankAccountDAO();

    public void addTransaction(){
        System.out.println("Transaction Type");
        Scanner scanner = new Scanner(System.in);
        String type = scanner.nextLine();
        System.out.println("Transaction Amount");
        Double amount = scanner.nextDouble();
        System.out.println("Bank Account ID");
        int bankAccId = scanner.nextInt();
        BankAccount bankAccount = bankAccountDAO.getBankAccountById(bankAccId);        

        AccountTransaction accountTransaction = new AccountTransaction(bankAccount, type, amount);
        
        accountTransactionDAO.saveTransaction(accountTransaction);
    }
}
