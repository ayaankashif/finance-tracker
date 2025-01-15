package com.ayaan;
import com.ayaan.FinanceTracker.service.MenuService;

public class App {                                 
    public static void main (String[] args) {
        MenuService menu = new MenuService();                  
        menu.financeMenu();       
    }
}
