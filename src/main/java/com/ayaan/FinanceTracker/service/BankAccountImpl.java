package com.ayaan.FinanceTracker.service;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.ayaan.FinanceTracker.dao.BankAccountDAO;
import com.ayaan.FinanceTracker.models.BankAccount;

public class BankAccountImpl {
    BankAccountDAO bankAccountDAO = new BankAccountDAO();

    public void addBankAccount() {
        System.out.println("Bank Name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        BankAccount bankAccount = new BankAccount(name, new Date(System.currentTimeMillis()));
        
        bankAccountDAO.saveBankAccount(bankAccount);
    }

    public void updateBankAccount() {
        System.out.println("Bank Name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Bank Account ID: ");
        Integer id = scanner.nextInt();
        BankAccount bankAccount = new BankAccount(id, name, new Date(System.currentTimeMillis()));

        bankAccountDAO.updateBankAccount(bankAccount);
    }

    public void listBankAccount() {
        try {
            List<BankAccount> bank = bankAccountDAO.getAllBankAccounts();
            System.out.println("Bank Account List: ");
            bank.forEach(bankAccount -> System.out.println(
                    bankAccount.getBankAccId() + " - " + bankAccount.getName() + " - " + bankAccount.getAccountDate()));
        } catch (Exception e) {
            System.out.println("No Bank Account Found");
            e.printStackTrace();
        }
    }
}