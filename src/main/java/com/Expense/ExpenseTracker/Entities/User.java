package com.Expense.ExpenseTracker.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;
    @Column(unique = true)
    private String email;
    private String password;
    private String MobNo;







}
