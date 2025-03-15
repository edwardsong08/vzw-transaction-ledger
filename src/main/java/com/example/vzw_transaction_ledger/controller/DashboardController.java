package com.example.vzw_transaction_ledger.controller;

import com.example.vzw_transaction_ledger.model.User;
import com.example.vzw_transaction_ledger.model.Transaction;
import com.example.vzw_transaction_ledger.service.UserService;
import com.example.vzw_transaction_ledger.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        List<User> users = userService.getAllUsers();
        List<Transaction> transactions = transactionService.getAllTransactions();
        model.addAttribute("users", users);
        model.addAttribute("transactions", transactions);
        model.addAttribute("pageTitle", "Dashboard");
        return "dashboard";  // This now refers to dashboard.html
    }
}
