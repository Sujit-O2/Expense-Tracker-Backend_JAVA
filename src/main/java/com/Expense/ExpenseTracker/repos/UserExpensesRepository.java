package com.Expense.ExpenseTracker.repos;

import com.Expense.ExpenseTracker.Entities.UserExpenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExpensesRepository extends JpaRepository<UserExpenses,Integer> {
}
