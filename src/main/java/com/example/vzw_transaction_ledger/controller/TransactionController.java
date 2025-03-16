package com.example.vzw_transaction_ledger.controller;

import com.example.vzw_transaction_ledger.model.Transaction;
import com.example.vzw_transaction_ledger.service.TransactionService;
import com.example.vzw_transaction_ledger.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // We need UserService to validate if the user exists
    @Autowired
    private UserService userService;

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

    // Handle form submission for creating a new transaction with validation
    @PostMapping("/save")
    public String saveTransaction(@Valid @ModelAttribute Transaction transaction, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Create New Transaction");
            return "transactionForm";
        }
        // Custom validation: Check if the associated user exists
        if (transaction.getUser() == null || transaction.getUser().getId() == null ||
            !userService.getUserById(transaction.getUser().getId()).isPresent()) {
            result.rejectValue("user.id", "transaction.user.notFound", "User with the given ID does not exist.");
            model.addAttribute("pageTitle", "Create New Transaction");
            return "transactionForm";
        }
        try {
            transactionService.createTransaction(transaction);
        } catch (Exception ex) {
            result.reject("error", "An unexpected error occurred while saving the transaction.");
            model.addAttribute("pageTitle", "Create New Transaction");
            return "transactionForm";
        }
        return "redirect:/dashboard";
    }

    // Handle form submission for updating an existing transaction with validation
    @PostMapping("/update/{id}")
    public String updateTransaction(@PathVariable Long id, @Valid @ModelAttribute Transaction transaction, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Edit Transaction");
            return "transactionForm";
        }
        // You might also check here if the provided user exists.
        if (transaction.getUser() == null || transaction.getUser().getId() == null ||
            !userService.getUserById(transaction.getUser().getId()).isPresent()) {
            result.rejectValue("user.id", "transaction.user.notFound", "User with the given ID does not exist.");
            model.addAttribute("pageTitle", "Edit Transaction");
            return "transactionForm";
        }
        try {
            transactionService.updateTransaction(id, transaction);
        } catch (Exception ex) {
            result.reject("error", "An unexpected error occurred while updating the transaction.");
            model.addAttribute("pageTitle", "Edit Transaction");
            return "transactionForm";
        }
        return "redirect:/dashboard";
    }

    // Handle transaction deletion via GET for UI purposes
    @GetMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        try {
            transactionService.deleteTransaction(id);
        } catch (Exception ex) {
            // Optionally, you could log the error and redirect to an error page
        }
        return "redirect:/dashboard";
    }
}
