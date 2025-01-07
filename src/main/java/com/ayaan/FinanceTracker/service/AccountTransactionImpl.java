package com.ayaan.FinanceTracker.service;

import java.util.List;

import com.ayaan.FinanceTracker.dao.AccountTransactionDAO;
import com.ayaan.FinanceTracker.models.AccountTransaction;
import com.ayaan.FinanceTracker.models.BankAccount;

public class AccountTransactionImpl {

    AccountTransactionDAO accountTransactionDAO = new AccountTransactionDAO();
    AccountTransaction accountTransaction = new AccountTransaction();

    public void addTransaction(BankAccount bankAccount, String transactionType, Double transactionAmount) {
        try {
            AccountTransaction accountTransaction = new AccountTransaction(bankAccount, transactionType, transactionAmount, 
            new java.sql.Date(System.currentTimeMillis()));
            accountTransactionDAO.saveTransaction(accountTransaction);
            System.out.println("\nTransaction added successfully");

        } catch (Exception e) {
            System.out.println("\nError: Invalid input");
            e.printStackTrace();
        }
    }

    public void listBankTransaction() {
        try {
            List<Object[]> transaction = accountTransactionDAO.getBankTransaction();
            if (transaction == null || transaction.isEmpty()) {
                System.out.println("No Bank Transactions Found.");
                return;
            }
            System.out.println("\nBank Transaction List:");
            System.out.printf("%-15s %-15s %-15s%n",
                "Bank Name", "Amount", "Transaction Type");
            System.out.println("------------------------------------------");
            for (Object[] record : transaction) {
                String bankName = (String) record[0];
                Double transactionAmt = (Double) record[1];
                String transactionType = (String) record[2];

                System.out.printf("%-15s %-15s %-15s%n", bankName, transactionAmt, transactionType);
            }

        } catch (Exception e) {
            System.out.println("An error occurred while fetching bank transactions.");
            e.printStackTrace(); 
        }
    }

    public void bankStats(){
        try {
            List<Object[]> transaction = accountTransactionDAO.accountDashboard();
            if (transaction == null || transaction.isEmpty()) {
                System.out.println("Nothing to see ");
                return;
            }
            System.out.println("\nDashboard: ");
            System.out.printf("%-12s %-17s %-15s%n",
                "Bank ID ", "Bank Name ", "Amount");
            System.out.println("-----------------------------------");
            for (Object[] record : transaction) {
                Integer BankId = (Integer) record [0];
                String bankName = (String) record[1];
                Double transactionAmt  =  (Double) record[2];

                if (transactionAmt < 0 ){
                    transactionAmt  = 0.0;
                }
                
                System.out.printf("%-12s %-17s %-15s%n" , 
                            BankId, bankName, transactionAmt);
            }

        } catch (Exception e) {
            System.out.println("An error occurred while fetching bank transactions.");
            e.printStackTrace(); 
        }
    }

    public void monthlyCreditStats(){
        try {
            List<Object[]> transaction = accountTransactionDAO.monthlyStats("Credit");
            if (transaction == null || transaction.isEmpty()) {
                System.out.println("No Bank Transactions Found.");
                return;
            }
            System.out.println("\nCredit Amount\n");
            System.out.printf("%-12s %-17s%n",
                "Bank Name", "Amount");
            System.out.println("-------------------------");
            for (Object[] record : transaction) {
                String bankName = (String) record[0];
                Double transactionAmt = (Double) record[1];

                System.out.printf("%-12s %-17s%n", bankName, transactionAmt);
            }

        } catch (Exception e) {
            System.out.println("An error occurred while fetching bank transactions.");
            e.printStackTrace(); 
        }
    }

    public void monhtlyDebitStats(){
        try {
            List<Object[]> transaction = accountTransactionDAO.monthlyStats("Debit");
            if (transaction == null || transaction.isEmpty()) {
                System.out.println("No Bank Transactions Found.");
                return;
            }
            System.out.println("\nDebit Amount");
            System.out.printf("%-12s %-17s%n",
                "Bank Name", "Amount");
            System.out.println("-------------------------");
            for (Object[] record : transaction) {
                String bankName = (String) record[0];
                Double transactionAmt = (Double) record[1];

                System.out.printf("%-12s %-17s%n", bankName, transactionAmt);
            }

        } catch (Exception e) {
            System.out.println("An error occurred while fetching bank transactions.");
            e.printStackTrace(); 
        }
    }

    


}
