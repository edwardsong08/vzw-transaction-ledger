package com.example.vzw_transaction_ledger.controller;

import com.example.vzw_transaction_ledger.model.Transaction;
import com.example.vzw_transaction_ledger.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Display the new transaction form
    @GetMapping("/new")
    public String showTransactionForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("pageTitle", "Create New Transaction");
        return "transactionForm";
    }

    // Display the edit transaction form
    @GetMapping("/edit/{id}")
    public String showEditTransactionForm(@PathVariable Long id, Model model) {
        Transaction txn = transactionService.getTransactionById(id)
            .orElseThrow(() -> new RuntimeException("Transaction not found"));
        model.addAttribute("transaction", txn);
        model.addAttribute("pageTitle", "Edit Transaction");
        return "transactionForm";
    }

    // Handle form submission for creating a new transaction
    @PostMapping("/save")
    public String saveTransaction(@ModelAttribute Transaction transaction) {
        transactionService.createTransaction(transaction);
        return "redirect:/dashboard";
    }

    // Handle form submission for updating an existing transaction
    @PostMapping("/update/{id}")
    public String updateTransaction(@PathVariable Long id, @ModelAttribute Transaction transaction) {
        transactionService.updateTransaction(id, transaction);
        return "redirect:/dashboard";
    }

    // Handle transaction deletion via GET for UI purposes
    @GetMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return "redirect:/dashboard";
    }
}
