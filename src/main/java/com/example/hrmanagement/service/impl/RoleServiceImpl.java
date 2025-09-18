package com.example.hrmanagement.service.impl;

import com.example.hrmanagement.entity.RoleEntity;
import com.example.hrmanagement.exception.BadRequestException;
import com.example.hrmanagement.exception.ResourceNotFoundException;
import com.example.hrmanagement.repository.RoleRepository;
import com.example.hrmanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired private RoleRepository repo;

    @Override
    public RoleEntity create(String name) {
        if (repo.existsByName(name)) throw new BadRequestException("Role already exists");
        RoleEntity r = new RoleEntity(null, name);
        return repo.save(r);
    }

    @Override
    public List<RoleEntity> findAll() {
        return repo.findAll();
    }

    @Override
    public RoleEntity get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id));
    }

    @Override
    public RoleEntity update(Long id, String name) {
        RoleEntity current = get(id);
        // allow same name for same record; block collision with other record
        repo.findByName(name).ifPresent(other -> {
            if (!other.getId().equals(id)) throw new BadRequestException("Role already exists");
        });
        current.setName(name);
        return repo.save(current);
    }

    @Override
    public void delete(Long id) {
        RoleEntity current = get(id);
        repo.delete(current);
    }
}

