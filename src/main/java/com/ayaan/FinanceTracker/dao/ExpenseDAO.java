package com.ayaan.FinanceTracker.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ayaan.FinanceTracker.models.Expense;
import com.ayaan.FinanceTracker.util.HibernateUtil;

public class ExpenseDAO {

    public void saveExpense(Expense expense) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(expense);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateExpense(Expense expense) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(expense);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteExpense(Expense expense) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // BankAccount bankAccount = session.get(BankAccount.class, id);
            if (expense != null) {
                session.delete(expense);
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

    public Expense getExpensebyId(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Expense.class, id);
        }
    }

    public List<Expense> getAllExpense() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Expense", Expense.class).list();
        }
    }
}
