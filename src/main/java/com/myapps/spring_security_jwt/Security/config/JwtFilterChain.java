package com.myapps.spring_security_jwt.Security.config;

import com.myapps.spring_security_jwt.Security.repository.UserRepository;
import com.myapps.spring_security_jwt.Util.JWTUtil;
import com.myapps.spring_security_jwt.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Configuration
public class JwtFilterChain extends OncePerRequestFilter {

    private final JWTUtil jWTUtil;
    private final UserRepository userRepository;

    public JwtFilterChain(JWTUtil jWTUtil, UserRepository userRepository) {
        this.jWTUtil = jWTUtil;
        this.userRepository = userRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       final String authorization= request.getHeader("Authorization");
       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;

       String jwtToken=null;
       String userName=null;

       if(authorization!=null && authorization.startsWith("Bearer ")) {
           jwtToken=authorization.substring(7);
           userName =jWTUtil.extractUserName(jwtToken);
       }
       if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
            Optional<User> user= userRepository.findByUserName(userName);
            if(user.isPresent()) {
                if(jWTUtil.validateToken(jwtToken,user.get().getUsername())){
                    usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.get(),null,user.get().getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                }
            }
       }
       filterChain.doFilter(request, response);
    }
}
