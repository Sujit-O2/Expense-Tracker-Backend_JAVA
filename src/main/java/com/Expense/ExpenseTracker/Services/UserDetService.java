package com.Expense.ExpenseTracker.Services;

import com.Expense.ExpenseTracker.Entities.User;
import com.Expense.ExpenseTracker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetService implements UserDetailsService {
 @Autowired
 private UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User uu=repo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User Npt Found"));

        return new MyUser(uu);
    }
}
