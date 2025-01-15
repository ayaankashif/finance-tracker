package com.ayaan.FinanceTracker.service;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.ayaan.FinanceTracker.models.BudgetTracker;
import com.ayaan.FinanceTracker.daoImpl.BankAccountDAOImpl;
import com.ayaan.FinanceTracker.daoImpl.BudgetTrackerDAOImpl;
import com.ayaan.FinanceTracker.daoImpl.ExpenseDAOImpl;
import com.ayaan.FinanceTracker.daoImpl.IncomeExpenseSourcesDAOImpl;
import com.ayaan.FinanceTracker.models.AccountTransaction;
import com.ayaan.FinanceTracker.models.BankAccount;
import com.ayaan.FinanceTracker.models.Expense;
import com.ayaan.FinanceTracker.models.IncomeExpenseSources;

public class ExpenseService {
    IncomeExpenseSourcesDAOImpl incomeExpenseSourcesDAO = new IncomeExpenseSourcesDAOImpl();
    IncomeExpenseSourcesService incomeExpenseSourcesImpl = new IncomeExpenseSourcesService();
    BankAccountDAOImpl bankAccountDAO = new BankAccountDAOImpl();
    AccountTransactionService accountTransactionImpl = new AccountTransactionService();
    ExpenseDAOImpl expenseDAO = new ExpenseDAOImpl();
    AccountTransaction accountTransaction = new AccountTransaction();
    BudgetTrackerDAOImpl budgetTrackerDAO = new BudgetTrackerDAOImpl();

    public void addExpense() {
        try {
            System.out.println("Name:");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();

            System.out.println("Expense:");
            Double expense = scanner.nextDouble();
            if (expense > 0) {
                expense = -expense;
            }

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
                if (incomeExpenseSources == null) {
                    System.out.println("No Expense source found.");
                }
            }

            Expense expense1 = new Expense(name, bankAccount, expense, incomeExpenseSources,
                    new Date(System.currentTimeMillis()));

            accountTransactionImpl.addTransaction(bankAccount, "Debit", expense);
            accountTransaction.setTransactionAmt(accountTransaction.getTransactionAmt() + expense);

            expenseDAO.saveExpense(expense1);

            System.out.println("\nExpense added successfully");

        } catch (Exception e) {
            System.out.println("\nError: Invalid input");
            e.printStackTrace();
        }
    }

    public void updateExpense() {
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
        Double exp = scanner.nextDouble();
        if (exp > 0) {
            exp = -exp;
        }

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
        Expense ex = expenseDAO.getExpensebyId(id);
        if (ex == null) {
            System.out.println("No expense with this ID");
        }
        
        ex.setExpense(ex.getExpense() + exp);

        Expense expense = new Expense(id, name, bankAccount, exp, incomeExpenseSources,
                new Date(System.currentTimeMillis()));

        accountTransactionImpl.addTransaction(bankAccount, "Debit", exp);
        accountTransaction.setTransactionAmt(accountTransaction.getTransactionAmt() + exp);

        expenseDAO.updateExpense(expense);
        expenseDAO.updateExpense(ex);
    }

    public void addExpenseSource() {
        System.out.println("Expense Source:");
        Scanner scanner = new Scanner(System.in);
        String expenseSource = scanner.nextLine();

        BudgetTracker budgetTracker = null;
        while (budgetTracker == null) {
            System.out.println("Enter your budget Name to link this source: ");
            String name = scanner.nextLine();
            budgetTracker = budgetTrackerDAO.getBudgetByCondition(name);
            if (budgetTracker == null) {
                System.out.println("Error, no budget found ");
            }
        }

        incomeExpenseSourcesImpl.addIncomeExpenseSource(expenseSource, 'E', budgetTracker);
    }

    public void listExpenseSources() {
        try {
            List<IncomeExpenseSources> sources = incomeExpenseSourcesDAO.getAllIncomeExpenseSources('E');
            System.out.println("\nExpense Sources List: ");
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
            System.out.println("\nExpenses List: ");
            System.out.printf("%-17s %-15s%n",
                    "Source", "Expense");
            System.out.println("----------------------------------------------");

            expenses.forEach(expense -> System.out.printf("%-17s %-15s%n",
                    expense.getExpenseSourceId().getIncomeExpenseSource(),
                    expense.getExpense()));

        } catch (Exception e) {
            System.out.println("No Expense Found");
            e.printStackTrace();
        }
    }

}