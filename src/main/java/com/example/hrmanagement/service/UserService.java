package com.example.hrmanagement.service;

import com.example.hrmanagement.dto.UserRegisterRequest;
import com.example.hrmanagement.entity.UserEntity;

public interface UserService {
    UserEntity register(UserRegisterRequest req);
}
