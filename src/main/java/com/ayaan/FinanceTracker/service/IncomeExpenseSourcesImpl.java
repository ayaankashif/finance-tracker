package com.ayaan.FinanceTracker.service;

import com.ayaan.FinanceTracker.dao.IncomeExpenseSourcesDAO;
import com.ayaan.FinanceTracker.models.IncomeExpenseSources;

public class IncomeExpenseSourcesImpl {
    IncomeExpenseSourcesDAO incomeExpenseSourcesDAO = new IncomeExpenseSourcesDAO();

    public void addIncomeExpenseSource(String incomeExpenseSource, char type) {
        try {
            IncomeExpenseSources incomeExpenseSources = new IncomeExpenseSources(incomeExpenseSource, type);
            incomeExpenseSourcesDAO.saveIncomeExpenseSources(incomeExpenseSources);
            System.out.println("\nIncome/Expense Source added successfully");

        } catch (Exception e) {
            System.out.println("Error: Invalid input");
            e.printStackTrace();
    }
}

}
