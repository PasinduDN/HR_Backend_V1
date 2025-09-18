package com.example.hrmanagement.service;

import com.example.hrmanagement.entity.RoleEntity;
import java.util.List;

public interface RoleService {
    RoleEntity create(String name);
    List<RoleEntity> findAll();
    RoleEntity get(Long id);
    RoleEntity update(Long id, String name);
    void delete(Long id);
}

