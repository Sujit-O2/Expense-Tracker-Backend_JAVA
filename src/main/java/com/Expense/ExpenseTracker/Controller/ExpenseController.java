package com.Expense.ExpenseTracker.Controller;

import com.Expense.ExpenseTracker.Entities.UserExpenses;
import com.Expense.ExpenseTracker.Services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("/add")
    public UserExpenses addExpense(@RequestBody UserExpenses expense, Authentication aa) {
        return expenseService.addExpense(expense);
    }

    @GetMapping("/all")
    public List<UserExpenses> getAllExpenses(Authentication aa) {
        return expenseService.getAllExpenses();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteExpense(@PathVariable int id,Authentication aa) {
        expenseService.deleteExpense(id);
    }
//    @GetMapping("totalBal")
}
