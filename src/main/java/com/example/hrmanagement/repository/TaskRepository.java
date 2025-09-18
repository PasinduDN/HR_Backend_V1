package com.example.hrmanagement.repository;

import com.example.hrmanagement.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByLabors_Id(Long employeeId);
    List<TaskEntity> findByLeaders_Id(Long employeeId);
}
