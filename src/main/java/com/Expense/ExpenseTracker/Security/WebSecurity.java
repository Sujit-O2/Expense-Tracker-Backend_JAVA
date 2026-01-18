package com.Expense.ExpenseTracker.Security;

import com.Expense.ExpenseTracker.Services.UserDetService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurity {
    @Autowired
    private UserDetService userDetService;
    @Autowired
    private JwtAuth jwtAuth;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors(cross->cross.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(req->
                                req.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                                        .requestMatchers("/user/login","/user/signup").permitAll()
                                .anyRequest().authenticated()
                        )
                .exceptionHandling(
                        ex->
                                ex.authenticationEntryPoint(
                                        (request, response, authException)
                                                -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"unauth")))
                .sessionManagement(ses->ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuth, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration cc= new CorsConfiguration();
        cc.setAllowedOrigins(List.of("http://localhost:5173"));
        cc.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        cc.setMaxAge(3600L);
        cc.setAllowedHeaders(List.of("*"));
        cc.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource ca=new UrlBasedCorsConfigurationSource();
        ca.registerCorsConfiguration("/**",cc);
        return ca;


    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider ad=new DaoAuthenticationProvider(userDetService);
        ad.setPasswordEncoder(passWordEncoder());
        return  ad;
    }

    private PasswordEncoder passWordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration){
        return authenticationConfiguration.getAuthenticationManager();
    }


}
