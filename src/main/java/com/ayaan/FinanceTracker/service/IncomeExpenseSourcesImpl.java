package com.ayaan.FinanceTracker.service;

import com.ayaan.FinanceTracker.dao.IncomeExpenseSourcesDAO;
import com.ayaan.FinanceTracker.models.BudgetTracker;
import com.ayaan.FinanceTracker.models.IncomeExpenseSources;

public class IncomeExpenseSourcesImpl {
    IncomeExpenseSourcesDAO incomeExpenseSourcesDAO = new IncomeExpenseSourcesDAO();
   

    public void addIncomeExpenseSource(String incomeExpenseSource, char type, BudgetTracker budgetTracker ) {
        try {
            IncomeExpenseSources incomeExpenseSources = new IncomeExpenseSources(incomeExpenseSource, type, budgetTracker);
            incomeExpenseSourcesDAO.saveIncomeExpenseSource(incomeExpenseSources);
            System.out.println("\nIncome/Expense Source added successfully");

        } catch (Exception e) {
            System.out.println("Error: Invalid input");
            e.printStackTrace();
        }
    }

    public void monthlyGoal(String source,  Double goal){   
        try {
            incomeExpenseSourcesDAO.updateMonthlyBudget(source, goal);

        } catch (Exception e) {
            System.out.println("Error: Invalid Input ");
            e.printStackTrace();
        }
    }

}
