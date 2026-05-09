package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserRepository;
import com.example.demo.entity.User;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = repo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}