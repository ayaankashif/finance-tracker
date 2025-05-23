package com.ayaan.FinanceTracker.service;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayaan.FinanceTracker.dao.AccountTransactionDAO;
import com.ayaan.FinanceTracker.daoImpl.AccountTransactionDAOImpl;
import com.ayaan.FinanceTracker.daoImpl.BankAccountDAOImpl;
import com.ayaan.FinanceTracker.dao.BankAccountDAO;
import com.ayaan.FinanceTracker.exceptionHandling.BankAlreadyExistException;
import com.ayaan.FinanceTracker.exceptionHandling.DataAccessException;
import com.ayaan.FinanceTracker.models.AccountTransaction;
import com.ayaan.FinanceTracker.models.BankAccount;

public class BankAccountService {
    private Logger logger = LoggerFactory.getLogger(BankAccountService.class);

    BankAccountDAO bankAccountDAO = new BankAccountDAOImpl();
    AccountTransactionService accountTransactionImpl = new AccountTransactionService();
    AccountTransactionDAO accountTransactionDAO = new AccountTransactionDAOImpl();

    public void addBankAccount() {
        try {
            System.out.println("Bank Name: ");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            BankAccount bankAccount = bankAccountDAO.getBankAccountByCondition(name);
            if (bankAccount != null) {
            throw new BankAlreadyExistException("Bank Account already exists");
        }
        bankAccount = new BankAccount(name, new Date(System.currentTimeMillis()));
        bankAccountDAO.saveBankAccount(bankAccount); 

        AccountTransaction accountTransaction = accountTransactionDAO.getTransactionByBankAccountId(bankAccount.getBankAccId());
        System.out.println(bankAccount.getBankAccId());
        if (accountTransaction == null) {
            accountTransaction = new AccountTransaction();
            accountTransaction.setTransactionAmt(0.0);
            accountTransaction.setBankAccId(bankAccount);          
        }
        
        System.out.println("Transaction Amount: " + accountTransaction.getTransactionAmt() +
                           " | Account Holder: " + accountTransaction.getBankAccId().getName());

        } catch (BankAlreadyExistException e) {
            logger.error("Error while giving bank Account: {}", e.getMessage());
        } catch (InputMismatchException e) {
            logger.error("Invalid input: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected error occurred: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateBankAccount() {
        try {
            System.out.println("Bank Name: ");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            System.out.println("Bank Account ID: ");
            Integer id = scanner.nextInt();
            
            BankAccount bankAccount = new BankAccount(id, name, new Date(System.currentTimeMillis()));
            bankAccountDAO.updateBankAccount(bankAccount);
            logger.info("Bank Account Updated");

        } catch (InputMismatchException e) {
            logger.error("Invalid input: {}", e.getMessage());
        } catch(Exception e){
            logger.error("An unexpented error occured: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    public void listBankAccount() {
        try {
            List<BankAccount> bank = bankAccountDAO.getAllBankAccounts();
            if (bank == null) {
                throw new DataAccessException("No bank Account Found!");
            }
            System.out.println("Bank Account List: ");
            System.out.printf("%-15s %-14s %-15s%n",
                    "Bank ID", "Bank Name", "Account Date");
            System.out.println("----------------------------------------------");
            bank.forEach(bankAccount -> System.out.printf("%-15s %-14s %-15s%n",
                    bankAccount.getBankAccId(), bankAccount.getName(), bankAccount.getAccountDate()));

        } catch (DataAccessException e) {
            logger.error("Database error while fetching income sources: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected error occured: {}", e.getMessage());
            e.printStackTrace();
        }
    }

}