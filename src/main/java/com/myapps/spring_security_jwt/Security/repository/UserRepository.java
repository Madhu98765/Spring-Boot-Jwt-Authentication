package com.myapps.spring_security_jwt.Security.repository;

import com.myapps.spring_security_jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String username);
}
