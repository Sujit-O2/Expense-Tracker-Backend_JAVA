package com.Expense.ExpenseTracker.Services;

import com.Expense.ExpenseTracker.Dtos.SignUpDto;
import com.Expense.ExpenseTracker.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.Expense.ExpenseTracker.repos.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;
    private BCryptPasswordEncoder bb=new BCryptPasswordEncoder(12);


    public User GetUser(String mail) {

        return repo.findByEmail(mail).orElseThrow(()->new UsernameNotFoundException("No User"));
    }

    public ResponseEntity<?> setUser(SignUpDto signUpDto) {
        boolean exst= repo.existsByEmail(signUpDto.getEmail());
        if(!exst){
            User uu=new User();
            uu.setEmail(signUpDto.getEmail());
            uu.setUserName(signUpDto.getUserName());
            uu.setMobNo(signUpDto.getMobNo());
            uu.setPassword(bb.encode(signUpDto.getPassword()));
            repo.save(uu);
            return ResponseEntity.ok("Account Created");
        }
        return ResponseEntity.status(401).body("User exists");
    }
}
