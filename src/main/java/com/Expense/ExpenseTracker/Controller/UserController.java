package com.Expense.ExpenseTracker.Controller;

import com.Expense.ExpenseTracker.Dtos.LoginDto;
import com.Expense.ExpenseTracker.Dtos.SignUpDto;

import com.Expense.ExpenseTracker.Entities.User;
import com.Expense.ExpenseTracker.Services.Jwts.jwtTokenGenVld;
import com.Expense.ExpenseTracker.Services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private jwtTokenGenVld  JwtTokenGenVld;
    @PostMapping("/login")
    public ResponseEntity<?> LogIn(@RequestBody LoginDto loginDto, HttpServletResponse httpServletResponse){
        System.out.println(loginDto.getName()+loginDto.getPassword());
        Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getName(),loginDto.getPassword()));
        if(auth.isAuthenticated()){
            User uu=userService.GetUser(loginDto.getName());
            String token=JwtTokenGenVld.GenerateToken(uu);
            System.out.println("a "+token);
            System.out.println("god");
            Cookie cc=new Cookie("token",token);
            System.out.println("sujit12");

            cc.setHttpOnly(true);
            cc.setSecure(false);
            cc.setPath("/");
            cc.setMaxAge(24*60*60);
            httpServletResponse.addCookie(cc);
            System.out.println("sujit");

            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto){

        return userService.setUser(signUpDto);
    }
}
