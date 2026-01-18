package com.Expense.ExpenseTracker.Entities;

import com.Expense.ExpenseTracker.Entities.Enums.ExpenseType;
import jakarta.persistence.*;
import lombok.Data;
@Table
@Entity
@Data
public class UserExpenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int refNo;

    private String title;
    private double amount;

    @Enumerated(EnumType.STRING)
    private ExpenseType type;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
