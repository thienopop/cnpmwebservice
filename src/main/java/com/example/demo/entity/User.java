
package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users") // <--- Thêm dòng này để đổi tên bảng thành "users" (hoặc "app_users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name="name",nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String role;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

//
//CREATE TABLE "Users" (
//user_id SERIAL PRIMARY KEY,
//name VARCHAR(100),
//email VARCHAR(150) UNIQUE NOT NULL,
//password VARCHAR(255) NOT NULL,
//role VARCHAR(50),
//created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
//);

