package com.myapps.spring_security_jwt.Security.config;

import com.myapps.spring_security_jwt.Security.service.CustomUserDetailService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProvider implements AuthenticationManager {
    private final CustomUserDetailService customUserDetailService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationProvider(CustomUserDetailService customUserDetailService, PasswordEncoder passwordEncoder) {
        this.customUserDetailService=customUserDetailService;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails newUser= customUserDetailService. loadUserByUsername(authentication.getName());
        if(!passwordEncoder.matches((String)authentication.getCredentials(),newUser.getPassword()))
            throw new BadCredentialsException("Bad credentials");
        return authentication;
    }
}
