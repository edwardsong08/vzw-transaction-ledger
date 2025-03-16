package com.example.vzw_transaction_ledger.controller;

import com.example.vzw_transaction_ledger.model.User;
import com.example.vzw_transaction_ledger.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Display the new user form
    @GetMapping("/new")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Create New User");
        return "userForm";
    }

    // Display the edit user form
    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Edit User");
        return "userForm";
    }

    // Handle form submission for creating a new user with validation
    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        // Check for validation errors from bean validation
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Create New User");
            return "userForm";
        }
        // Custom validation: check for duplicate email
        if (userService.emailExists(user.getEmail())) {
            result.rejectValue("email", "user.email.duplicate", "Email already exists.");
            model.addAttribute("pageTitle", "Create New User");
            return "userForm";
        }
        try {
            userService.createUser(user);
        } catch (DataIntegrityViolationException ex) {
            // In case the repository throws an exception due to unique constraints etc.
            result.rejectValue("email", "user.email.duplicate", "Email already exists.");
            model.addAttribute("pageTitle", "Create New User");
            return "userForm";
        } catch (Exception ex) {
            // Catch any other unexpected exception
            result.reject("error", "An unexpected error occurred while saving the user.");
            model.addAttribute("pageTitle", "Create New User");
            return "userForm";
        }
        return "redirect:/dashboard";
    }

    // Handle form submission for updating an existing user with validation
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Edit User");
            return "userForm";
        }
        // Optionally, you could check if the email is being changed and if it duplicates another record.
        try {
            userService.updateUser(id, user);
        } catch (Exception ex) {
            result.reject("error", "An unexpected error occurred while updating the user.");
            model.addAttribute("pageTitle", "Edit User");
            return "userForm";
        }
        return "redirect:/dashboard";
    }

    // Handle user deletion via GET for UI purposes
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/dashboard";
    }
}
