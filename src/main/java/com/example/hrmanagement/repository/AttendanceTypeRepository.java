package com.example.hrmanagement.repository;

import com.example.hrmanagement.entity.AttendanceTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttendanceTypeRepository extends JpaRepository<AttendanceTypeEntity, Long> {
    boolean existsByName(String name);
    Optional<AttendanceTypeEntity> findByName(String name);
}

