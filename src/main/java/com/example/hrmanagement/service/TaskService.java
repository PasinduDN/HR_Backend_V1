package com.example.hrmanagement.service;

import com.example.hrmanagement.dto.Task;
import com.example.hrmanagement.entity.TaskEntity;
import java.util.List;

public interface TaskService {
    TaskEntity createTask(Task taskDTO);
    TaskEntity getTaskById(Long id);
    List<TaskEntity> getAllTasks();
    TaskEntity updateTask(Long id, Task taskDTO);
    void deleteTask(Long id);
    List<TaskEntity> getTasksByEmployee(Long employeeId);
}
