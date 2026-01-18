package com.Expense.ExpenseTracker.Services.Jwts;

import com.Expense.ExpenseTracker.Entities.User;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class jwtTokenGenVld {
    private final SecretKey key;
    public jwtTokenGenVld(@Value("${jwt.secret}") String secret){
        this.key=new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),"HmacSHA256");
    }
    public String GenerateToken(User a){
        return Jwts.builder()
                .subject(a.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(key).compact();
    }
    public String getUser(String token){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload().getSubject();

    }
    public boolean ValidateToken(String Token, UserDetails userDetails){
        String username=getUser(Token);
        return username.equals(userDetails.getUsername())&&!isTokenExpaired(Token);
    }

    private boolean isTokenExpaired(String token) {
        Date exp=Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload().getExpiration();

        return exp.before(new Date());
    }
}
