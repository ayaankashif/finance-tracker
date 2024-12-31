package com.ayaan.FinanceTracker.service;

import java.util.Scanner;

public class Menu {
    BankAccountImpl bankAccountImpl = new BankAccountImpl();   
    AccountTransactionImpl accountTransactionImpl = new AccountTransactionImpl();   
    
    public void financeMenu(){
        System.out.println("\nFinance Tracker\n ");
        System.out.println("1. Bank Account");
        System.out.println("2. Deposit Money");
        System.out.println("2. Account Management");
        Scanner scanner = new Scanner(System.in);
        Integer choice = scanner.nextInt();

        switch (choice) {
            case 1:
                bankMenu();
                financeMenu();
                break;
            case 2:
                transactionMenu();
                financeMenu();
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
    }

    public void bankMenu(){
        System.out.println("Bank Account Menu\n ");
        System.out.println("1. Add Bank Account");
        System.out.println( "2. Update Bank Account");
        System.out.println("3. List Bank Account");
        Scanner scanner = new Scanner(System.in);  
        Integer input = scanner.nextInt();

        switch (input) {
            case 1:
                bankAccountImpl.addBankAccount();
                break;
            case 2:
                bankAccountImpl.updateBankAccount();
                break;  
            case 3: 
                bankAccountImpl.listBankAccount();
                break;

            default:
                System.out.println("Invalid Choice");
                break;
        }
    }


    public void transactionMenu(){
        System.out.println("Transaction Menu\n ");
        System.out.println("1. Add Transaction");
        System.out.println("2. List Transaction");
        Scanner scanner = new Scanner(System.in);
        Integer input = scanner.nextInt();

        switch (input) {
            case 1:
                accountTransactionImpl.addTransaction();
                break;
            case 2:
                
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
    }

}
