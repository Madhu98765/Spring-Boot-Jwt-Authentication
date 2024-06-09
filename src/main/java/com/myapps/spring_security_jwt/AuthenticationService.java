package com.myapps.spring_security_jwt;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {
    public boolean validateUser(){
        //return user.getUsername().equals("Madhu") && user.getPassword().equals("Madhu");
        return true;
    }
}
