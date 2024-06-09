package com.myapps.spring_security_jwt.Security.controller;

import com.myapps.spring_security_jwt.Security.config.AuthenticationProvider;
import com.myapps.spring_security_jwt.Security.service.AuthService;
import com.myapps.spring_security_jwt.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/authenticate")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final AuthenticationProvider authenticationProvider;

    AuthController(PasswordEncoder passwordEncoder, AuthService authService, AuthenticationProvider authenticationProvider) {
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
        this.authenticationProvider = authenticationProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) throws Exception {
        authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        return ResponseEntity.ok(authService.loginUser(user));
    }
    @PostMapping("/registerUser")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        String encodedPassword= passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        String response= authService.registerUser(user);
        return ResponseEntity.ok(response);
    }
}
