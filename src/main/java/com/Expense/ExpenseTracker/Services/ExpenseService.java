package com.Expense.ExpenseTracker.Services;

import com.Expense.ExpenseTracker.Entities.User;
import com.Expense.ExpenseTracker.Entities.UserExpenses;
import com.Expense.ExpenseTracker.repos.UserExpensesRepository;
import com.Expense.ExpenseTracker.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    @Autowired
    private final UserExpensesRepository repository;
    @Autowired
    private final UserRepo repo;

    public UserExpenses addExpense(UserExpenses expense) {
        MyUser uu1= (MyUser) Objects.
                requireNonNull(SecurityContextHolder.
                        getContext().
                        getAuthentication()).
                getPrincipal();
        User uu=repo.findByEmail(uu1.getUsername()).orElseThrow(()->new UsernameNotFoundException("Uese not found"));
        expense.setUser(uu);
        return repository.save(expense);
    }

    public List<UserExpenses> getAllExpenses() {
        return repository.findAll();
    }

    public void deleteExpense(int id) {
        repository.deleteById(id);
    }
}
