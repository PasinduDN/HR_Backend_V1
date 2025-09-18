package com.example.hrmanagement.service;

import com.example.hrmanagement.entity.AttendanceTypeEntity;
import java.util.List;

public interface AttendanceTypeService {
    AttendanceTypeEntity create(String name);
    AttendanceTypeEntity get(Long id);
    AttendanceTypeEntity update(Long id, String name);
    void delete(Long id);
    List<AttendanceTypeEntity> findAll();
}

