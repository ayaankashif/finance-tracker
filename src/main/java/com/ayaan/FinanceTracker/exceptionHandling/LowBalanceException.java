package com.ayaan.FinanceTracker.exceptionHandling;

public class LowBalanceException extends RuntimeException {
    public LowBalanceException(String message) {
        super(message);
    }   
}
