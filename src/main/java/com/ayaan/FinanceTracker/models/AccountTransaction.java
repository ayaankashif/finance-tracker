package com.ayaan.FinanceTracker.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "account_transaction")
public class AccountTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;

    @ManyToOne
    @JoinColumn(name = "bank_acc_id") //maps to the bankAccount ID
    private BankAccount bankAccId;

    @Column(name = "transaction_type")    
    private String transactionType;

    @Column(name = "transaction_amount")    
    private Double transactionAmt;

    public AccountTransaction() {
    }

    public AccountTransaction(BankAccount bankAccId, String transactionType, Double transactionAmt) {
        this.bankAccId = bankAccId;
        this.transactionType = transactionType;
        this.transactionAmt = transactionAmt;
    }
    
    public AccountTransaction(Integer transactionId, BankAccount bankAccId, String transactionType, Double transactionAmt) {
        this(bankAccId, transactionType, transactionAmt);
        this.transactionId = transactionId;
    }
    
    public Integer getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }
    public BankAccount getBankAccId() {
        return bankAccId;
    }
    public void setBankAccId(BankAccount bankAccId) {
        this.bankAccId = bankAccId;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public Double getTransactionAmt() {
        return transactionAmt;
    }
    public void setTransactionAmt(Double transactionAmt) {
        this.transactionAmt = transactionAmt;
    }
}
