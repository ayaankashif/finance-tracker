package com.ayaan.FinanceTracker.service;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayaan.FinanceTracker.daoImpl.AccountTransactionDAOImpl;
import com.ayaan.FinanceTracker.daoImpl.BankAccountDAOImpl;
import com.ayaan.FinanceTracker.dao.AccountTransactionDAO;
import com.ayaan.FinanceTracker.dao.BankAccountDAO;
import com.ayaan.FinanceTracker.daoImpl.IncomeDAOImpl;
import com.ayaan.FinanceTracker.dao.IncomeDAO;
import com.ayaan.FinanceTracker.daoImpl.IncomeExpenseSourcesDAOImpl;
import com.ayaan.FinanceTracker.dao.IncomeExpenseSourcesDAO;
import com.ayaan.FinanceTracker.exceptionHandling.DataAccessException;
import com.ayaan.FinanceTracker.exceptionHandling.InvalidIDException;
import com.ayaan.FinanceTracker.exceptionHandling.invalidInputException;
import com.ayaan.FinanceTracker.models.AccountTransaction;
import com.ayaan.FinanceTracker.models.BankAccount;
import com.ayaan.FinanceTracker.models.Income;
import com.ayaan.FinanceTracker.models.IncomeExpenseSources;

public class IncomeService {
    private static final Logger logger = LoggerFactory.getLogger(IncomeService.class);

    IncomeDAO incomeDAO = new IncomeDAOImpl();
    BankAccountDAO bankAccountDAO = new BankAccountDAOImpl();
    AccountTransactionService accountTransactionImpl = new AccountTransactionService();
    IncomeExpenseSourcesService incomeExpenseSourcesImpl = new IncomeExpenseSourcesService();
    IncomeExpenseSourcesDAO incomeExpenseSourcesDAO = new IncomeExpenseSourcesDAOImpl();
    AccountTransaction accountTransaction = new AccountTransaction();
    IncomeExpenseSources incomeExpenseSources = new IncomeExpenseSources();
    AccountTransactionDAO accountTransactionDAO = new AccountTransactionDAOImpl();

    public void addIncome() {
        try {
            System.out.println("Name: ");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();

            BankAccount bankAccount = null;
            while (bankAccount == null) {
                System.out.println("Bank Account: ");
                String bankAcc = scanner.nextLine();
                bankAccount = bankAccountDAO.getBankAccountByCondition(bankAcc);
                if (bankAccount == null) {
                    throw new DataAccessException(
                            "\nError: No Bank account found. Please enter a valid bank account name.");
                }
            }

            IncomeExpenseSources incomeExpenseSources = null;
            while (incomeExpenseSources == null) {
                System.out.println("Income Source: ");
                String incomeSource = scanner.nextLine();
                incomeExpenseSources = incomeExpenseSourcesDAO.getIncomeExpenseSourceByCondition(incomeSource);
                if (incomeExpenseSources == null) {
                    throw new DataAccessException("\nError: No Income Source found.");
                }
            }

            System.out.println("Income: ");
            Double income = scanner.nextDouble();
                
            accountTransaction.setTransactionAmt(accountTransaction.getTransactionAmt() + income);
            accountTransactionImpl.addTransaction(bankAccount, "Credit", income);
            
            Income income1 = new Income(name, bankAccount, income, incomeExpenseSources,
            new Date(System.currentTimeMillis()));

            incomeDAO.saveIncome(income1);
            logger.info("\nIncome added successfully");

        } catch (IllegalArgumentException e) {
            logger.error("Invalid income value: {}", e.getMessage());
        } catch (DataAccessException e) {
            logger.error("Database access error: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected error occurred: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateIncome() {
        try {
            System.out.println("Name: ");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();

            BankAccount bankAccount = null;
            while (bankAccount == null) {
                System.out.println("Bank Account: ");
                String bankAcc = scanner.nextLine();
                bankAccount = bankAccountDAO.getBankAccountByCondition(bankAcc);
                if (bankAccount == null) {
                    throw new DataAccessException(
                            "\nError: No Bank account found. Please enter a valid bank account name.");
                }
            }

            System.out.println("Income: ");
            Double income = scanner.nextDouble();

            IncomeExpenseSources incomeExpenseSources = null;
            while (incomeExpenseSources == null) {
                System.out.println("Income Source: ");
                String incomeSource = scanner.nextLine();
                incomeExpenseSources = incomeExpenseSourcesDAO.getIncomeExpenseSourceByCondition(incomeSource);
                if (incomeExpenseSources == null) {
                    logger.info("No Income Source Found!");
                }
            }

            System.out.println("Income ID: ");
            Integer id = scanner.nextInt();
            Income existingIncome = incomeDAO.getIncomebyId(id);

            if (!existingIncome.getName().equalsIgnoreCase(name)) {
                throw new InvalidIDException("The ID doesn't match your records. Aborting update");
            }

            System.out.println("\nPress '1' to add income into your existing amount.\nPress '2' to Rectify your actual amount");
            Integer choice = scanner.nextInt();

            if (choice == 1) {
                Double updatedAmount = existingIncome.getIncome() + income;
                existingIncome.setIncome(updatedAmount);
                logger.info("Updated Total Income: " + updatedAmount);
                accountTransaction.setTransactionAmt(accountTransaction.getTransactionAmt() + income);
                accountTransactionImpl.addTransaction(bankAccount, "Credit", income);
                incomeDAO.updateIncome(existingIncome);

            } else if (choice == 2) {
                Income income1 = new Income(id, name, bankAccount, income, incomeExpenseSources,
                        new Date(System.currentTimeMillis()));
                        
                incomeDAO.updateIncome(income1);
            } else {
                throw new invalidInputException("Invalid choice ");
            }
        } catch (invalidInputException e) {
            logger.error("Invalid input found: {}", e.getMessage());
        } catch (InvalidIDException e) {
            logger.error("No ID found: {}", e.getMessage());
        } catch (NoSuchElementException e) {
            logger.error("No matching element found: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Invalid data provided: {}", e.getMessage());
        } catch (DataAccessException e) {
            logger.error("Error accessing the database: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected error occurred: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    public void addIncomeSource() {
        try {
            System.out.println("Income Source: ");
            Scanner scanner = new Scanner(System.in);
            String incomeSource = scanner.nextLine();

            incomeExpenseSourcesImpl.addIncomeExpenseSource(incomeSource, 'I', null);
            logger.info("Income Source Added");

        } catch (IllegalArgumentException e) {
            logger.error("Invalid income source: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected error occurred: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    public void listIncome() {
        try {
            List<Income> incomes = incomeDAO.getAllIncome();
            if (incomes == null) {
                throw new DataAccessException("NO Incomes Found");
            }

            System.out.println("\nIncomes List: ");
            System.out.printf("%-12s %-17s %-15s %-15s%n",
                    "Income ID", "Name", "Income Source", "Income");
            System.out.println("---------------------------------------------------");
            incomes.forEach(income -> System.out.printf("%-12s %-17s %-15s %-15s%n",
                    income.getIncomeId(),
                    income.getName(),
                    income.getIncomeSources().getIncomeExpenseSource(),
                    income.getIncome()));

        } catch (DataAccessException e) {
            logger.error("Database error while fetching income sources: {}", e.getMessage());
        } catch (Exception e) {
            logger.info("An unexpected error occurred.");
            e.printStackTrace();
        }
    }
}
