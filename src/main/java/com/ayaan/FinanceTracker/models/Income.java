package com.ayaan.FinanceTracker.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "incomes")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "income_id")
    private Integer incomeId;
    private String name;

    @ManyToOne
	@JoinColumn(name = "bank_acc_id")
    private  BankAccount bankAccId;
    
    @Column(name = "income")
    private double income;
    
    @Column(name = "income_sources") 
    private String incomeSources;
    
    @Column(name = "income_date")
    private Date date;

    public Income(){
        
    }

    public Income(String name, BankAccount bankAccId, double income, String incomeSources, Date date) {
        this.name = name;
        this.bankAccId = bankAccId;
        this.income = income;
        this.incomeSources = incomeSources;
        this.date = date;
    }

    public Income(Integer incomeId, String name, BankAccount bankAccId, double income, String incomeSources, Date date) {
        this(name, bankAccId, income, incomeSources, date);
        this.incomeId = incomeId;
    }

    public Integer getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Integer incomeId) {
        this.incomeId = incomeId;
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

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public String getIncomeSources() {
        return incomeSources;
    }

    public void setIncomeSources(String incomeSources) {
        this.incomeSources = incomeSources;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
