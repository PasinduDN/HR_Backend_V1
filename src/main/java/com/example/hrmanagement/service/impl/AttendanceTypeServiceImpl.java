package com.example.hrmanagement.service.impl;

import com.example.hrmanagement.entity.AttendanceTypeEntity;
import com.example.hrmanagement.exception.BadRequestException;
import com.example.hrmanagement.exception.ResourceNotFoundException;
import com.example.hrmanagement.repository.AttendanceTypeRepository;
import com.example.hrmanagement.service.AttendanceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceTypeServiceImpl implements AttendanceTypeService {
    @Autowired private AttendanceTypeRepository repo;

    @Override
    public AttendanceTypeEntity create(String name) {
        if (repo.existsByName(name)) throw new BadRequestException("Attendance type exists");
        return repo.save(new AttendanceTypeEntity(null, name));
    }

    @Override
    public AttendanceTypeEntity get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance type not found with id " + id));
    }

    @Override
    public AttendanceTypeEntity update(Long id, String name) {
        AttendanceTypeEntity current = get(id);
        repo.findByName(name).ifPresent(other -> {
            if (!other.getId().equals(id)) throw new BadRequestException("Attendance type exists");
        });
        current.setName(name);
        return repo.save(current);
    }

    @Override
    public void delete(Long id) {
        repo.delete(get(id));
    }

    @Override
    public List<AttendanceTypeEntity> findAll() {
        return repo.findAll();
    }
}

