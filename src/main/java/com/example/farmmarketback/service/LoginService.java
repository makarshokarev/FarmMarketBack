package com.example.farmmarketback.service;

import com.example.farmmarketback.repository.FarmMarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private FarmMarketRepository farmMarketRepository;

    public boolean validate(String userName, String rawPassword){
        String encodedPassword = farmMarketRepository.getPassword(userName);
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
