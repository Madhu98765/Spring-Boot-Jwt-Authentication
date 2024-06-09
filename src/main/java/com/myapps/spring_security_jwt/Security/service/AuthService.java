package com.myapps.spring_security_jwt.Security.service;

import com.myapps.spring_security_jwt.Security.repository.UserRepository;
import com.myapps.spring_security_jwt.Util.JWTUtil;
import com.myapps.spring_security_jwt.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;

    public AuthService(UserRepository userRepository, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }
    public String registerUser(User user) {
        try{
            userRepository.save(user);
        }catch (Exception e){
            return e.getMessage();
        }
        return "Save Successful";
    }
    public String loginUser (User user) {
        return jwtUtil.generateToken(user.getUsername());
    }
}
