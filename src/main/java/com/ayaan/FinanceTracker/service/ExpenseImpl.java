package com.ayaan.FinanceTracker.service;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.ayaan.FinanceTracker.dao.BankAccountDAO;
import com.ayaan.FinanceTracker.dao.ExpenseDAO;
import com.ayaan.FinanceTracker.dao.IncomeExpenseSourcesDAO;
import com.ayaan.FinanceTracker.models.BankAccount;
import com.ayaan.FinanceTracker.models.Expense;
import com.ayaan.FinanceTracker.models.IncomeExpenseSources;

public class ExpenseImpl {
    IncomeExpenseSourcesDAO incomeExpenseSourcesDAO = new IncomeExpenseSourcesDAO();
    IncomeExpenseSourcesImpl incomeExpenseSourcesImpl = new IncomeExpenseSourcesImpl();
    BankAccountDAO bankAccountDAO = new BankAccountDAO();
    AccountTransactionImpl accountTransactionImpl = new AccountTransactionImpl();
    ExpenseDAO expenseDAO = new ExpenseDAO();

    public void addExpense() {
        try {
            System.out.println("Name:");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();

            System.out.println("Expense:");
            Double expense = scanner.nextDouble();

            BankAccount bankAccount = null;
            while (bankAccount == null) {
                System.out.println("Bank Account:");
                String bankAcc = scanner.nextLine();
                bankAccount = bankAccountDAO.getBankAccountByCondition(bankAcc);
                if (bankAccount == null) {
                    System.out.println("\nError: No Bank account found. Please enter a valid bank account name.");
                }
            }

            IncomeExpenseSources incomeExpenseSources = null;
            while (incomeExpenseSources == null) {
                System.out.println("Expense Source:");
                String expenseSource = scanner.nextLine();
                incomeExpenseSources = incomeExpenseSourcesDAO.getIncomeExpenseSourceByCondition(expenseSource);
                if (incomeExpenseSources == null ) {
                    System.out.println("No Expense source found.");
                }
            }

            Expense expense1 = new Expense(name, bankAccount, expense, incomeExpenseSources, incomeExpenseSources.getIncomeExpenseSource(), 
                    new Date(System.currentTimeMillis()));

            accountTransactionImpl.addTransaction(bankAccount, "Debit", expense);

            expenseDAO.saveExpense(expense1);
            System.out.println("\nExpense added successfully");

        } catch (Exception e) {
            System.out.println("\nError: Invalid input");
            e.printStackTrace();
        }
    }

    public void updateExpense(){
        System.out.println("Name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        BankAccount bankAccount = null;
        while (bankAccount == null) {
            System.out.println("Bank Account: ");
            String bankAcc = scanner.nextLine();
            bankAccount = bankAccountDAO.getBankAccountByCondition(bankAcc);
            if (bankAccount == null) {
                System.out.println(" No bank Account found.");
            }
        }
        System.out.println("Expense");
        Double expens = scanner.nextDouble();

        IncomeExpenseSources incomeExpenseSources = null;
        while (incomeExpenseSources == null) {
            System.out.println("Expense Source: ");
            String source = scanner.nextLine();
            incomeExpenseSources = incomeExpenseSourcesDAO.getIncomeExpenseSourceByCondition(source);
            if (incomeExpenseSources == null) {
                System.out.println(" No Expense Source found.");
            }
        }

        System.out.println("Expense ID: ");
        Integer id = scanner.nextInt();
        Expense expense = new Expense(id, name, bankAccount, expens, incomeExpenseSources, incomeExpenseSources.getIncomeExpenseSource(),
                new Date(System.currentTimeMillis()));
        expenseDAO.updateExpense(expense);
    }

    public void addExpenseSource() {
        System.out.println("Expense Source:");
        Scanner scanner = new Scanner(System.in);
        String expenseSource = scanner.nextLine();
        incomeExpenseSourcesImpl.addIncomeExpenseSource(expenseSource, 'E');
    }

    public void listExpenseSources() {
        try {
            List<IncomeExpenseSources> sources = incomeExpenseSourcesDAO.getAllIncomeExpenseSources('E');
            System.out.println("\nIncome/Expense Sources List: ");
            System.out.printf("%-15s%n",
             "Sources");
            System.out.println("------------");

            sources.forEach(incomeExpenseSources -> System.out.printf("%-15s%n",
            incomeExpenseSources.getIncomeExpenseSource()));
        } catch (Exception e) {
            System.out.println("No Sources Found");
            e.printStackTrace();
        }
    }

    public void listExpense() {
        try {
            List<Expense> expenses = expenseDAO.getAllExpense();
            System.out.println("\nIncome/Expense Sources List: ");
            System.out.printf("%-12s %-17s %-15s%n",
            "Expense ID", "Name", "Expense");
            System.out.println("----------------------------------------------");

            expenses.forEach(expense -> System.out.printf("%-12s %-17s %-15s%n",
            expense.getExpenseId(),
            expense.getName(),
            expense.getExpense() ));
        } catch (Exception e) {
            System.out.println("No Expense Found");
            e.printStackTrace();
        }
    }


    


}