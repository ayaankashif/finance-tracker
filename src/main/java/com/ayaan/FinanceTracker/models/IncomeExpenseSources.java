package com.ayaan.FinanceTracker.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "income_expense_sources")
public class IncomeExpenseSources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "income_exp_src_id")
    private Integer incomeExpenseSourceId;

    @Column(name = "income_expense_src")
    private String incomeExpenseSource;

    @Column(name = "type")
    private char type;
    
    public IncomeExpenseSources(){
        
    }
    
    public IncomeExpenseSources(String incomeExpenseSource, char type) {
        this.incomeExpenseSource = incomeExpenseSource;
        this.type = type;
    }

    public Integer getIncomeExpenseSourceId() {
        return incomeExpenseSourceId;
    }
    public void setIncomeExpenseSourceId(Integer incomeExpenseSourceId) {
        this.incomeExpenseSourceId = incomeExpenseSourceId;
    }
    
    public String getIncomeExpenseSource() {
        return incomeExpenseSource;
    }
    public void setIncomeExpenseSource(String incomeExpenseSource) {
        this.incomeExpenseSource = incomeExpenseSource;
    }
    public char getType() {
        return type;
    }
    public void setType(char type) {
        this.type = type;
    }
}
