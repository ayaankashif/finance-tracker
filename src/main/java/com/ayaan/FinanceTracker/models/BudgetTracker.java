package com.ayaan.FinanceTracker.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "budget_tracker")
public class BudgetTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_tracker_id")
    private Integer budgetTrackerId ;
    @Column(name = "name")
    private String name;
    @Column(name = "budget_percentage")
    private Integer budgetPercentage;
    @Column(name = "monthly_budget")
    private double monthlyBudget;
    @Column(name = "current_month")
    private double currentMonth;
    @Column(name = "remaining")
    private double remaining;

    public BudgetTracker(){
        
    }

    public Integer getBudgetTrackerId() {
        return budgetTrackerId;
    }
    
    public void setBudgetTrackerId(Integer budgetTrackerId) {
        this.budgetTrackerId = budgetTrackerId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getBudgetPercentage() {
        return budgetPercentage;
    }
    public void setBudgetPercentage(Integer budgetPercentage) {
        this.budgetPercentage = budgetPercentage;
    }
    public double getMonthlyBudget() {
        return monthlyBudget;
    }
    public void setMonthlyBudget(double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
    }
    public double getCurrentMonth() {
        return currentMonth;
    }
    public void setCurrentMonth(double currentMonth) {
        this.currentMonth = currentMonth;
    }
    public double getRemaining() {
        return remaining;
    }
    public void setRemaining(double remaining) {
        this.remaining = remaining;
    }
}
