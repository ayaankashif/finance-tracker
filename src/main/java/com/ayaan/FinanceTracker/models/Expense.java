package com.ayaan.FinanceTracker.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
    private Integer expenseId;
    @Column(name = "expense_source_id")
    private ExpenseSource expenseSourceId;
    @Column(name = "name")
	private String name;
    @Column(name = "bank_acc_id")
	private BankAccount bankAccId; 
    @Column(name = "expense")
    private double expense;
    @Column(name = "expense_sources")
    private String expenseSources;
    @Column(name = "date")
    private Date date;

    public Expense(){
        
    }

    public Integer getExpenseId() {
        return expenseId;
    }
    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }
    public ExpenseSource getExpenseSourceId() {
        return expenseSourceId;
    }
    public void setExpenseSourceId(ExpenseSource expenseSourceId) {
        this.expenseSourceId = expenseSourceId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BankAccount getBankAccId() {
        return bankAccId;
    }
    public void setBankAccId(BankAccount bankAccId) {
        this.bankAccId = bankAccId;
    }
    public double getExpense() {
        return expense;
    }
    public void setExpense(double expense) {
        this.expense = expense;
    }
    public String getExpenseSources() {
        return expenseSources;
    }
    public void setExpenseSources(String expenseSources) {
        this.expenseSources = expenseSources;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

}
