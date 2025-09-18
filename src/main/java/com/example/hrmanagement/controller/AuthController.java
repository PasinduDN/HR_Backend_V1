package com.example.hrmanagement.controller;

import com.example.hrmanagement.dto.AuthRequest;
import com.example.hrmanagement.dto.AuthResponse;
import com.example.hrmanagement.dto.UserRegisterRequest;
import com.example.hrmanagement.entity.UserEntity;
import com.example.hrmanagement.security.JwtUtil;
import com.example.hrmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            String token = jwtUtil.generateToken((org.springframework.security.core.userdetails.User) auth.getPrincipal());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).body(new AuthResponse("Invalid username or password"));
        }
    }

    // Register a system USER (ADMIN/HR) – not an employee/laborer
    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserRegisterRequest req) {
        UserEntity created = userService.register(req);
        return ResponseEntity.ok(created);
    }
}


