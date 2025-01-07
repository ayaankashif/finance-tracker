package com.ayaan.FinanceTracker.service;

import java.util.Scanner;

import com.ayaan.FinanceTracker.dao.AccountTransactionDAO;
import com.ayaan.FinanceTracker.dao.BankAccountDAO;

public class Menu {
    BankAccountImpl bankAccountImpl = new BankAccountImpl();
    AccountTransactionImpl accountTransactionImpl = new AccountTransactionImpl();
    IncomeImpl incomeImpl = new IncomeImpl();
    BankAccountDAO bankAccountDAO = new BankAccountDAO();
    ExpenseImpl expenseImpl = new ExpenseImpl();
    AccountTransactionDAO accountTransactionDAO = new AccountTransactionDAO();

    public void financeMenu() {
        System.out.println("\nFinance Tracker\n ");
        System.out.println("1. Dashboard");
        System.out.println("2. Bank Account");
        System.out.println("3. Income ");
        System.out.println("4. Expense ");
        System.out.println("5. Exit");
        Scanner scanner = new Scanner(System.in);
        Integer choice = scanner.nextInt();

        switch (choice) {
            case 1:
                dashboard();
                financeMenu();
                break;
            case 2:
                bankMenu();
                financeMenu();
                break;
            case 3:
                incomeMenu();
                financeMenu();
                break;
            case 4:
                expenseMenu();
                financeMenu();
                break;
            case 5: 
                System.out.println("Exiting the finance Tracker.");
                break;
            default:
                System.out.println("Invalid Choice");
                financeMenu();
                break;
        }
    }

    public void bankMenu() {
        System.out.println("Bank Account Menu\n ");
        System.out.println("1. Add Bank Account");
        System.out.println("2. Bank Transactions");
        System.out.println("3. Update Bank Account");
        System.out.println("4. List Bank Account");
        Scanner scanner = new Scanner(System.in);
        Integer input = scanner.nextInt();

        switch (input) {
            case 1:
                bankAccountImpl.addBankAccount();
                break;
            case 2:
                accountTransactionImpl.listBankTransaction();
                break;
            case 3:
                bankAccountImpl.updateBankAccount();
                break;
            case 4:
                bankAccountImpl.listBankAccount();
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
    }

    public void incomeMenu() {
        System.out.println("Income Menu\n ");
        System.out.println("1. Add Income Source");
        System.out.println("2. Add Income");
        System.out.println("3. Update Income");
        System.out.println("4. List Income ");
        System.out.println("5. List Income Sources");
        Scanner scanner = new Scanner(System.in);
        Integer input = scanner.nextInt();

        switch (input) {
            case 1:
                incomeImpl.addIncomeSource();
                break;
            case 2:
                incomeImpl.addIncome();
                break;
            case 3:
                incomeImpl.updateIncome();
                break;
            case 4:
                incomeImpl.listIncome();
                break;
            case 5:
                incomeImpl.listSources();
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
    }

    public void expenseMenu() {
        System.out.println("Expense Menu\n ");
        System.out.println("1. Add Expense Source");
        System.out.println("2. Add Expense");
        System.out.println("3. Update Expense");
        System.out.println("4. List Expense");
        System.out.println("5. List Expense Sources");
        Scanner scanner = new Scanner(System.in);
        Integer input = scanner.nextInt();

        switch (input) {
            case 1:
                expenseImpl.addExpenseSource();
                break;
            case 2:
                expenseImpl.addExpense();
                break;
            case 3:
                expenseImpl.updateExpense();
                break;
            case 4:
                expenseImpl.listExpense();
                break;
            case 5:
                expenseImpl.listExpenseSources();
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
    }

    public void dashboard() {
        System.out.println("\nDashboard:");
        System.out.println("1. Bank Stats");
        System.out.println("2. Monthly Credits");
        System.out.println("3. Monthly Debits");
        Scanner scanner = new Scanner(System.in);
        Integer choice = scanner.nextInt();

        switch (choice) {
            case 1:
                accountTransactionImpl.bankStats();
                break;
            case 2:
                accountTransactionImpl.monthlyCreditStats();
                break;
            case 3:
                accountTransactionImpl.monhtlyDebitStats();
                break;
            default:
                break;
        }
    }
}
