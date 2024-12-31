package com.ayaan.FinanceTracker.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "expense_sources")
public class ExpenseSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_source_id")
    private Integer expenseSourceId;
    @Column(name = "expense_source")
    private String expenseSource;

    public ExpenseSource(){
        
    }

    public Integer getExpenseSourceId() {
        return expenseSourceId;
    }
    public void setExpenseSourceId(Integer expenseSourceId) {
        this.expenseSourceId = expenseSourceId;
    }
    public String getExpenseSource() {
        return expenseSource;
    }
    public void setExpenseSource(String expenseSource) {
        this.expenseSource = expenseSource;
    }
}
