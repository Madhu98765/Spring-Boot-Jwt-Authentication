package com.myapps.spring_security_jwt.CustomException;

public class UsernameNotFoundException extends Exception{ // user UserDetailsException
    public UsernameNotFoundException(String msg){
        super(msg);
    }
}
