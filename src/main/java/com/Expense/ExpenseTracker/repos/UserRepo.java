package com.Expense.ExpenseTracker.repos;

import com.Expense.ExpenseTracker.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    boolean existsByEmail(String name);

    Optional<User> findByEmail(String name);
}
