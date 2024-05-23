package com.olympics.ticketing.service;

import com.olympics.ticketing.dto.LoginDTO;
import com.olympics.ticketing.exception.CustomException;
import com.olympics.ticketing.model.User;
import com.olympics.ticketing.repository.UserRepository;
import com.olympics.ticketing.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    public String authenticate(LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(), loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();
            return jwtTokenUtil.generateToken(user);
        } catch (Exception e) {
            throw new CustomException("Invalid username or password");
        }
    }
}
