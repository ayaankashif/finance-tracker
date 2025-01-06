package com.ayaan.FinanceTracker.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ayaan.FinanceTracker.models.IncomeExpenseSources;
import com.ayaan.FinanceTracker.util.HibernateUtil;

public class IncomeExpenseSourcesDAO {

    public void saveIncomeExpenseSources(IncomeExpenseSources incomeExpenseSources) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(incomeExpenseSources);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateIncomeExpenseSources(IncomeExpenseSources incomeExpenseSources) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(incomeExpenseSources);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteIncome(Integer id) {   
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            IncomeExpenseSources incomeExpenseSources = session.get(IncomeExpenseSources.class, id);
            if (incomeExpenseSources != null) {
                session.delete(incomeExpenseSources);
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

    public IncomeExpenseSources getIncomeExpenseSourceByCondition(String source) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM IncomeExpenseSources WHERE incomeExpenseSource = :source";
            return session.createQuery(hql, IncomeExpenseSources.class)
                          .setParameter("source", source)
                          .uniqueResult();
        }
    }

    public IncomeExpenseSources getIncomeExpenseSourcesbyId(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(IncomeExpenseSources.class, id);
        }
    }

    public List<IncomeExpenseSources> getAllIncomeExpenseSources(char type) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM IncomeExpenseSources WHERE type = :type";
            return session.createQuery(hql, IncomeExpenseSources.class)
                          .setParameter("type", type)
                          .list();
        }
    }   
}
