package com.ayaan.FinanceTracker.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ayaan.FinanceTracker.models.Expense;
import com.ayaan.FinanceTracker.daoImpl.BudgetTrackerDAOImpl;
import com.ayaan.FinanceTracker.daoImpl.ExpenseDAOImpl;
import com.ayaan.FinanceTracker.daoImpl.IncomeDAOImpl;
import com.ayaan.FinanceTracker.daoImpl.IncomeExpenseSourcesDAOImpl;
import com.ayaan.FinanceTracker.models.AccountTransaction;
import com.ayaan.FinanceTracker.models.BudgetTracker;
import com.ayaan.FinanceTracker.models.IncomeExpenseSources;

public class BudgetTrackerService {

    IncomeService incomeImpl = new IncomeService();
    IncomeExpenseSourcesService incomeExpenseSourcesImpl = new IncomeExpenseSourcesService();
    IncomeExpenseSources incomeExpenseSources = new IncomeExpenseSources();
    IncomeExpenseSourcesDAOImpl incomeExpenseSourcesDAO = new IncomeExpenseSourcesDAOImpl();
    BudgetTrackerDAOImpl budgetTrackerDAO = new BudgetTrackerDAOImpl();
    IncomeDAOImpl incomeDAO = new IncomeDAOImpl();
    ExpenseDAOImpl expenseDAO = new ExpenseDAOImpl();
    AccountTransaction accountTransaction = new AccountTransaction();

    public void incomeOverview() {
        Scanner scanner = new Scanner(System.in);

        String source = null;
        IncomeExpenseSources incomeExpenseSources = null;
        while (incomeExpenseSources == null) {
            System.out.println("Income Source: ");
            source = scanner.nextLine();
            incomeExpenseSources = incomeExpenseSourcesDAO.getIncomeExpenseSourceByCondition(source);
            if (incomeExpenseSources == null) {
                System.out.println("\nError: No Income Source found.");
            }
        }

        Double goal = null;
        while (goal == null) {
            System.out.println("Enter your Monthly Goal: ");
            try {
                goal = scanner.nextDouble();
                if (goal <= 0) {
                    System.out.println("Error: Monthly Goal must be a positive number.");
                    goal = null; // Reset to retry
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a numeric value.");
                scanner.nextLine();
            }
        }

        incomeExpenseSourcesImpl.monthlyGoal(source, goal);
        Double incomeValue = incomeDAO.getIncomeBySourceFromIncomes(source);

        Double remaining = goal - incomeValue;
        Double progress = (incomeValue / goal) * 100;

        BudgetTracker budgetTracker = new BudgetTracker(
                null,
                remaining,
                null,
                progress);

        if (budgetTracker != null) {
            System.out.println("Your remaining amount is " + remaining);
            System.out.println("Your progress is " + progress + "%");
            System.out.println("Income Updated. ");
        }
    }

    public void calculateBudget(Double budget) {
        Double income = incomeDAO.getIncomes();
        Double expense = expenseDAO.getExpenses();
        Double monthlyBudget = (income * budget) / 100; // divide income as per percentage
        Double remaining = monthlyBudget + expense;

        if (monthlyBudget != null) {
            System.out.println("Your Monthly Budget for is " + monthlyBudget);
        }
        System.out.println("your remaining amount is " + remaining);
    }

    public void budget() {
        System.out.println("Budget Name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        System.out.println("Budget % :  ");
        Double budget = scanner.nextDouble();

        BudgetTracker budgetTracker = new BudgetTracker(
                name,
                null,
                budget,
                null);

        budgetTrackerDAO.saveBudget(budgetTracker);
    }

    public void expenseOverview() {
        Scanner scanner = new Scanner(System.in);

        String expenseSource = null;
        IncomeExpenseSources incomeExpenseSources = null;
        while (incomeExpenseSources == null) {
            System.out.println("Expense Source: ");
            expenseSource = scanner.nextLine();
            incomeExpenseSources = incomeExpenseSourcesDAO.getIncomeExpenseSourceByCondition(expenseSource);
            if (incomeExpenseSources == null) {
                System.out.println("\nError: No Income Source found.");
            }
        }

        Double budget = null;
        while (budget == null) {
            System.out.println("Enter your Monthly Budget: ");
            try {
                budget = scanner.nextDouble();
                if (budget <= 0) {
                    System.out.println("Error: Monthly Goal must be a positive number.");
                    budget = null; // Reset to retry
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a numeric value.");
                scanner.nextLine();
            }
        }

        incomeExpenseSourcesImpl.monthlyGoal(expenseSource, budget);
        Double expenseValue = expenseDAO.getExpenseBySourceFromExpense(expenseSource);

        Double remaining = budget + expenseValue;
        expenseValue = Math.abs(expenseValue);
        Double progress = (expenseValue / budget) * 100;

        BudgetTracker budgetTracker2 = new BudgetTracker(
                null,
                remaining,
                null,
                progress);

        if (budgetTracker2 != null) {
            System.out.println("Your remaining amount is " + remaining);
            System.out.println("Your progress is " + progress + "%");
            System.out.println("Expense Updated");
        }
    }

    // public void incomeOvervie1w() {
    // Scanner scanner = new Scanner(System.in);

    // String incomeSource;
    // IncomeExpenseSources incomeExpenseSources = null;
    // while (incomeExpenseSources == null) {
    // System.out.println("Income Source: ");
    // incomeSource = scanner.nextLine().trim();

    // if (incomeSource.isEmpty()) {
    // System.out.println("\nError: Income Source cannot be empty. Please try
    // again.");
    // continue;
    // }

    // incomeExpenseSources =
    // incomeExpenseSourcesDAO.getIncomeExpenseSourceByCondition(incomeSource);
    // if (incomeExpenseSources == null) {
    // System.out.println("\nError: No Income Source found. Please try again.");
    // }
    // }

    // Double goal = null;
    // while (goal == null) {
    // System.out.println("Enter your Monthly Goal: ");
    // try {
    // goal = scanner.nextDouble();
    // scanner.nextLine(); // Consume leftover newline
    // if (goal <= 0) {
    // System.out.println("Error: Monthly Goal must be a positive number.");
    // goal = null; // Reset to retry
    // }
    // } catch (InputMismatchException e) {
    // System.out.println("Error: Invalid input. Please enter a numeric value.");
    // scanner.nextLine(); // Clear invalid input
    // }
    // }

    // incomeExpenseSourcesImpl.monthlyGoal(incomeSource, goal);

    // // Ensure `income` is initialized and has a valid value
    // if (income == null || income.getIncome() == null || income.getIncome() <= 0)
    // {
    // System.out.println("Error: Current income is not set or invalid.");
    // return;
    // }

    // // Calculate remaining income
    // double remaining = income.getIncome() - goal;
    // if (remaining < 0) {
    // System.out.println("Warning: Monthly Goal exceeds current income. Proceeding
    // with negative remaining.");
    // }
    // income.setIncome(remaining);

    // // Calculate progress as a percentage
    // Double progress = (goal > 0) ? (remaining / goal) * 100 : 0.0;

    // // Create BudgetTracker object
    // BudgetTracker budgetTracker = new BudgetTracker(
    // null,
    // incomeExpenseSources,
    // remaining,
    // null,
    // progress);

    // // Save the budget tracker
    // budgetTrackerDAO.saveBudget(budgetTracker);
    // System.out.println("Income Updated Successfully.");
    // }

    public void IncomeOverviewDisplay() {
        try {
            List<Object[]> budgetTracker = budgetTrackerDAO.displayIncome();
            if (budgetTracker == null || budgetTracker.isEmpty()) {
                System.out.println("Nothing to see ");
                return;
            }
            System.out.println("\nIncome Overview ");
            System.out.printf("%-15s %-15s %-15s %-15s %-15s%n",
                    "Name", "Current Month", "Monthly Goal", "Remaining", "Progress");
            System.out.println("------------------------------------------------------------------------");
            for (Object[] record : budgetTracker) {
                String Name = (String) record[0];
                Double currentMonth = (Double) record[1];
                Double monthlyGoal = (Double) record[2];

                Double remaining = 0.0;
                Double progress = 0.0;
                if (incomeExpenseSources.getMonthlyBudget() != null && currentMonth != null || monthlyGoal != null) {
                    remaining = monthlyGoal - currentMonth;
                    progress = currentMonth / monthlyGoal * 100;
                }

                System.out.printf("%-15s %-15s %-15s %-15s %-15.2f%n",
                        Name, currentMonth, monthlyGoal, remaining, progress);
            }

        } catch (Exception e) {
            System.out.println("An error occurred while fetching bank transactions.");
            e.printStackTrace();
        }
    }

    public void budgetOverview() {
        List<BudgetTracker> budgets = budgetTrackerDAO.getAllBudgets();
        List<Expense> expenses = expenseDAO.getAllExpense();
        List<IncomeExpenseSources> incomeExpenseSources = incomeExpenseSourcesDAO.getAllIncomeExpenseSource();

        Double totalIncome = incomeDAO.getIncomes();
        if (totalIncome == null) {
            System.out.println("Total income is null.");
            return;
        }

        System.out.printf("%-17s %-20s %-15s %-15s %-15s%n", "Name", "Budget Percentage ", "Monthly Budget",
                "Current Month", "Remaining");
        System.out.println(
                "---------------------------------------------------------------------------------------------------");

        for (BudgetTracker budget : budgets) {
            String name = budget.getName();
            Double budgetPer = budget.getBudgetPercentage();

            Double allocatedAmount = 0.0;
            if (totalIncome != null && budgetPer != null) {
                allocatedAmount = (totalIncome * budgetPer) / 100;
            }

            Double currentExpense = 0.0;
            for (IncomeExpenseSources sources : incomeExpenseSources) {
                if (sources.getBudgetTracker() != null
                        && sources.getBudgetTracker().getBudgetTrackerId().equals(budget.getBudgetTrackerId())) {
                    for (Expense expense : expenses) {
                        if (expense.getExpenseSourceId().getIncomeExpenseSourceId()
                                .equals(sources.getIncomeExpenseSourceId())) {
                            currentExpense += expense.getExpense();
                        }
                    }
                }
            }

            Double remaining = allocatedAmount + currentExpense;

            System.out.printf("%-17s %-20.2f %-15.2f %-15s %-15.2f%n", name, budgetPer, allocatedAmount,
                    Math.abs(currentExpense),
                    remaining);
        }
    }

    public void expenseOverviewDisplay() {
        try {
            List<Object[]> budgetTracker = budgetTrackerDAO.displayExpense();
            if (budgetTracker == null || budgetTracker.isEmpty()) {
                System.out.println("Nothing to see ");
                return;
            }
            System.out.println("\nExpense Overview ");
            System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s%n",
                    "Name", "Current Month", "Monthly Goal", "Remaining", "Progress", "Budget Tracker");
            System.out.println("-----------------------------------------------------------------------------------------------");
            for (Object[] record : budgetTracker) {
                String Name = (String) record[0];
                Double currentMonth = (Double) record[1];
                Double monthlyBudget = (Double) record[2];
                String budgetName = (String) record[3]; 

                Double remaining = 0.0;
                Double progress = 0.0;
                if (incomeExpenseSources.getMonthlyBudget() != null && currentMonth != null
                        || monthlyBudget != null) {
                    remaining = monthlyBudget + currentMonth;
                    progress = (Math.abs(currentMonth) / monthlyBudget) * 100;
                }

                System.out.printf("%-15s %-15s %-15s %-15s %-15.2f %-15s%n",
                        Name, Math.abs(currentMonth), monthlyBudget, remaining, progress, budgetName);
            }

        } catch (Exception e) {
            System.out.println("An error occurred while fetching bank transactions.");
            e.printStackTrace();
        }
    }

}
