package com.example.FarmMarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return User.withUsername("test")
                .password(passwordEncoder.encode("test"))
                .roles("USER").build();
    }

    public boolean validate(String userName, String rawPassword){
        String encodedPassword = ""; //from DB by userName
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
