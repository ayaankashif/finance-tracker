package com.ayaan;

// import java.sql.Date;
// import java.util.List;

// import com.ayaan.FinanceTracker.dao.BankAccountDAO;
// import com.ayaan.FinanceTracker.models.BankAccount;
// import com.ayaan.FinanceTracker.dao.AccountTransactionDAO;
// import com.ayaan.FinanceTracker.models.AccountTransaction;
// import com.ayaan.FinanceTracker.service.AccountTransactionImpl;
// import com.ayaan.FinanceTracker.service.BankAccountImpl;
import com.ayaan.FinanceTracker.service.Menu;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){
        
        Menu menu = new Menu();
        menu.financeMenu();

        // BankAccountDAO bankAccountDAO = new BankAccountDAO();

        // BankAccountImpl bankAccountImpl = new BankAccountImpl();
        // bankAccountImpl.addBankAccount();
        // bankAccountImpl.updateBankAccount();

        // BankAccount bankAccount = new BankAccount();
        // bankAccount.setAccountDate(new Date(System.currentTimeMillis()));
        // bankAccountDAO.updateBankAccount(bankAccount);
        //bankAccountDAO.saveBankAccount(bankAccount);
        

        // AccountTransactionImpl accounttransaction = new AccountTransactionImpl();
        // accounttransaction.addTransaction();
        // accounttransaction.addTransaction();
        //AccountTransactionDAO accountTransactionDAO = new AccountTransactionDAO();
        // AccountTransaction accountTransaction = new AccountTransaction();
        
        // bankAccount = bankAccountDAO.getBankAccountById(1);
        // accountTransaction.setBankAccId(bankAccount);
        // accountTransaction.setTransactionAmt(3000.00);
        // accountTransaction.setTransactionType("Credit");
        
        // accountTransactionDAO.saveTransaction(accountTransaction);

    }

}
