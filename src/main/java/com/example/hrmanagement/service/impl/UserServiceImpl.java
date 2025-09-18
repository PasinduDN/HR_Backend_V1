package com.example.hrmanagement.service.impl;

import com.example.hrmanagement.dto.UserRegisterRequest;
import com.example.hrmanagement.entity.UserEntity;
import com.example.hrmanagement.entity.UserRole;
import com.example.hrmanagement.exception.BadRequestException;
import com.example.hrmanagement.repository.UserRepository;
import com.example.hrmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserEntity register(UserRegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new BadRequestException("Email already in use");
        }
        UserEntity u = new UserEntity();
        u.setEmail(req.getEmail());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        u.setFirstName(req.getFirstName());
        u.setLastName(req.getLastName());
        u.setRole(req.getRole() == null ? UserRole.HR : req.getRole());
        return userRepository.save(u);
    }
}
