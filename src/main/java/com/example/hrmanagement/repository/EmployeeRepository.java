package com.example.hrmanagement.repository;

import com.example.hrmanagement.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    Optional<EmployeeEntity> findByEmail(String email);
}