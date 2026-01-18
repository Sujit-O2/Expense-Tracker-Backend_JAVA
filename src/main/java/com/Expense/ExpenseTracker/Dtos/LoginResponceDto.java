package com.Expense.ExpenseTracker.Dtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class LoginResponceDto {
    private String userName;
    private String email;
    private String MobNo;

}
