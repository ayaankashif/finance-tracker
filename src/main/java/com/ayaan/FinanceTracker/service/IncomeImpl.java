package com.ayaan.FinanceTracker.service;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.ayaan.FinanceTracker.dao.BankAccountDAO;
import com.ayaan.FinanceTracker.dao.IncomeDAO;
import com.ayaan.FinanceTracker.dao.IncomeExpenseSourcesDAO;
import com.ayaan.FinanceTracker.models.AccountTransaction;
import com.ayaan.FinanceTracker.models.BankAccount;
import com.ayaan.FinanceTracker.models.Income;
import com.ayaan.FinanceTracker.models.IncomeExpenseSources;

public class IncomeImpl {

    IncomeDAO incomeDAO = new IncomeDAO();
    BankAccountDAO bankAccountDAO = new BankAccountDAO();
    AccountTransactionImpl accountTransactionImpl = new AccountTransactionImpl();
    IncomeExpenseSourcesImpl incomeExpenseSourcesImpl = new IncomeExpenseSourcesImpl();
    IncomeExpenseSourcesDAO incomeExpenseSourcesDAO = new IncomeExpenseSourcesDAO();
    AccountTransaction accountTransaction = new AccountTransaction();
    IncomeExpenseSources incomeExpenseSources = new IncomeExpenseSources();

    public void addIncome() {
        try {
            System.out.println("Name: ");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            incomeExpenseSources.setIncomeExpenseSource(name);
            incomeExpenseSources.setType('I');
            incomeExpenseSourcesDAO.saveIncomeExpenseSource(incomeExpenseSources);

            BankAccount bankAccount = null;
            while (bankAccount == null) {
                System.out.println("Bank Account: ");
                String bankAcc = scanner.nextLine();
                bankAccount = bankAccountDAO.getBankAccountByCondition(bankAcc);
                if (bankAccount == null) {
                    System.out.println("\nError: No Bank account found. Please enter a valid bank account name.");
                }
            }

            IncomeExpenseSources incomeExpenseSources = null;
            while (incomeExpenseSources == null) {
                System.out.println("Income Source: ");
                String incomeSource = scanner.nextLine();
                incomeExpenseSources = incomeExpenseSourcesDAO.getIncomeExpenseSourceByCondition(incomeSource);
                if (incomeExpenseSources == null) {
                    System.out.println("\nError: No Income Source found.");
                }
            }

            System.out.println("Income: ");
            Double income = scanner.nextDouble();
            accountTransaction.setTransactionAmt(accountTransaction.getTransactionAmt() + income);

            Income income1 = new Income(name, bankAccount, income, incomeExpenseSources,
                    new Date(System.currentTimeMillis()));
            accountTransactionImpl.addTransaction(bankAccount, "Credit", income);

            incomeDAO.saveIncome(income1);

            BudgetTrackerService budgetTrackerService = new BudgetTrackerService();
            budgetTrackerService.calculateBudget(income);

            System.out.println("\nIncome added successfully");

        } catch (Exception e) {
            System.out.println("\nError: Invalid input");
            e.printStackTrace();
        }
    }

    public void updateIncome() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Name: ");
        String name = scanner.nextLine();

        BankAccount bankAccount = null;
        while (bankAccount == null) {
            System.out.println("Bank Account: ");
            String bankAcc = scanner.nextLine();
            bankAccount = bankAccountDAO.getBankAccountByCondition(bankAcc);
            if (bankAccount == null) {
                System.out.println("\nError: No Bank account found. Please enter a valid bank account name.");
            }
        }

        System.out.println("Income: ");
        Double income = scanner.nextDouble();

        IncomeExpenseSources incomeExpenseSources = null;
        while (incomeExpenseSources == null) {
            System.out.println("Income Source: ");
            String incomeSource = scanner.nextLine();
            incomeExpenseSources = incomeExpenseSourcesDAO.getIncomeExpenseSourceByCondition(incomeSource);
            if (incomeExpenseSources == null) {
                System.out.println("\nError: No Income Source found.");
            }
        }

        System.out.println("Income ID: ");
        Integer id = scanner.nextInt();
        Income existingIncome = incomeDAO.getIncomebyId(id);
        if (existingIncome == null) {
            System.out.println("No Income found with this ID");
        }
        System.out.println("adding " + income + " to " + existingIncome.getIncome());

        Double updatedAmount = existingIncome.getIncome() + income;
        existingIncome.setIncome(updatedAmount);

        System.out.println("Updated Total Income: " + updatedAmount);
        
        Income income1 = new Income(id, name, bankAccount, income, incomeExpenseSources,
                new Date(System.currentTimeMillis()));

        accountTransaction.setTransactionAmt(accountTransaction.getTransactionAmt() + income);
        accountTransactionImpl.addTransaction(bankAccount, "Credit", income);

        incomeDAO.updateIncome(income1);
        incomeDAO.updateIncome(existingIncome); 
    }

    public void addIncomeSource() {
        System.out.println("Income Source: ");
        // Scanner scanner = new Scanner(System.in);
        // String incomeSource = scanner.nextLine();
        // incomeExpenseSourcesImpl.addIncomeExpenseSource(incomeSource, 'I');
    }

    public void listSources() {
        try {
            List<IncomeExpenseSources> sources = incomeExpenseSourcesDAO.getAllIncomeExpenseSources('I');
            System.out.println("\nIncome Sources List: ");
            System.out.printf("%-15s%n",
                    "Sources");
            System.out.println("-------------");
            sources.forEach(incomeExpenseSources -> System.out.printf("%-15s%n",
                    incomeExpenseSources.getIncomeExpenseSource()));
        } catch (Exception e) {
            System.out.println("No Sources Found");
            e.printStackTrace();
        }
    }

    public void listIncome() {
        try {
            List<Income> incomes = incomeDAO.getAllIncome();
            System.out.println("\nIncomes List: ");
            System.out.printf("%-12s %-17s %-15s %-15s%n",
                    "Income ID", "Name", "Income Source", "Income");
            System.out.println("---------------------------------------------------");
            incomes.forEach(income -> System.out.printf("%-12s %-17s %-15s %-15s%n",
                    income.getIncomeId(),
                    income.getName(),
                    income.getIncomeSources().getIncomeExpenseSource(),
                    income.getIncome()));

        } catch (Exception e) {
            System.out.println("No Expense Found");
            e.printStackTrace();
        }
    }
}
