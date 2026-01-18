package com.Expense.ExpenseTracker.Security;

import com.Expense.ExpenseTracker.Services.Jwts.jwtTokenGenVld;
import com.Expense.ExpenseTracker.Services.UserDetService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
@Component
public class JwtAuth extends OncePerRequestFilter {
    @Autowired
    private jwtTokenGenVld JwtTokenGenVli;
    @Autowired
    private UserDetService userDetService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
    String Token=null;
        Cookie[] cc=request.getCookies();
        if(cc!=null){
            Token = Arrays.stream(cc)
                    .filter(c->c.getName().equals("token"))
                    .map(c->c.getValue())
                    .findFirst()
                    .orElse("null");
            System.out.println(Token);

        }
        if(Token==null||Token.trim().isEmpty()){
            filterChain.doFilter(request,response);
            return;
        }
        try{
            String Usermail=JwtTokenGenVli.getUser(Token);
            if(Usermail!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails uu=userDetService.loadUserByUsername(Usermail);

                if(JwtTokenGenVli.ValidateToken(Token,uu)){
                    UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(uu,null,uu.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }catch (Exception e){
            System.out.println("Token Failed");
        }
        filterChain.doFilter(request,response);


    }
}
