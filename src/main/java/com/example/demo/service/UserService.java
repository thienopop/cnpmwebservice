package com.example.demo.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // CREATE
    public User create(User user) {
        return userRepository.save(user);
    }

    // READ ALL
    public List<User> getAll() {
        return userRepository.findAll();
    }

    // READ BY ID
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // UPDATE
    public User update(Long id, User newUser) {
        User user = getById(id);
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setRole(newUser.getRole());
        return userRepository.save(user);
    }

    // DELETE
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}