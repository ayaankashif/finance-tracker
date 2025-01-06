package com.ayaan.FinanceTracker.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ayaan.FinanceTracker.models.AccountTransaction;
import com.ayaan.FinanceTracker.util.HibernateUtil;

public class AccountTransactionDAO {

    public void saveTransaction(AccountTransaction accountTransaction) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(accountTransaction);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateTransaction(AccountTransaction accountTransaction) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(accountTransaction);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteTransactions(AccountTransaction accountTransaction) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // BankAccount bankAccount = session.get(BankAccount.class, id);
            if (accountTransaction != null) {
                session.delete(accountTransaction);
                System.out.println("Bank Account is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public AccountTransaction getTransactionById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(AccountTransaction.class, id);
        }
    }

    public List<Object[]> getBankTransaction() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT ba.name, at.transactionAmt, at.transactionType FROM AccountTransaction at " +
                         "JOIN at.bankAccId ba " +
                         "WHERE at.transactionDate BETWEEN :startDate AND :endDate";
            return session.createQuery(hql, Object[].class)
                          .setParameter("startDate", Date.valueOf("2025-01-01"))
                          .setParameter("endDate", Date.valueOf("2025-01-31"))
                          .list();
        }
    }
    

    public List <AccountTransaction> getAllTransactions() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from AccountTransaction", AccountTransaction.class).list();
        }
    }   
}
