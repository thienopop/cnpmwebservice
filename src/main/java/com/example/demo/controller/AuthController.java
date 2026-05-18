package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã tồn tại");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // encode password
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        // mặc định USER
        user.setRole("USER");

        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token, "Đăng ký thành công");
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy user")
                );

        boolean checkPassword = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!checkPassword) {
            throw new RuntimeException("Sai mật khẩu");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token, "Đăng nhập thành công");
    }
}