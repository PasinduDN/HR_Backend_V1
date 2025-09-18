package com.example.hrmanagement.service.impl;

import com.example.hrmanagement.dto.Task;
import com.example.hrmanagement.entity.EmployeeEntity;
import com.example.hrmanagement.entity.LocationEntity;
import com.example.hrmanagement.entity.TaskEntity;
import com.example.hrmanagement.exception.ResourceNotFoundException;
import com.example.hrmanagement.repository.EmployeeRepository;
import com.example.hrmanagement.repository.LocationRepository;
import com.example.hrmanagement.repository.TaskRepository;
import com.example.hrmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired private TaskRepository taskRepository;
    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private LocationRepository locationRepository;

    @Override
    @Transactional
    public TaskEntity createTask(Task dto) {
        TaskEntity t = new TaskEntity();
        map(t, dto);
        return taskRepository.save(t);
    }

    @Override
    @Transactional(readOnly = true)
    public TaskEntity getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    @Transactional
    public TaskEntity updateTask(Long id, Task dto) {
        TaskEntity t = getTaskById(id);
        map(t, dto);
        return taskRepository.save(t);
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        TaskEntity t = getTaskById(id);
        taskRepository.delete(t);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskEntity> getTasksByEmployee(Long employeeId) {
        // Using two derived methods and de-duplicating
        Set<TaskEntity> uniq = new LinkedHashSet<>();
        uniq.addAll(taskRepository.findByLabors_Id(employeeId));
        uniq.addAll(taskRepository.findByLeaders_Id(employeeId));
        return new ArrayList<>(uniq);
    }

    // ---------- helpers ----------

    /**
     * Maps non-null fields from DTO to entity.
     * NOTE:
     *  - If DTO collections are null, we leave the entity collections unchanged.
     *  - If DTO collections are an EMPTY set, we clear the entity collections.
     */
    private void map(TaskEntity t, Task dto) {
        if (dto.getTaskName() != null)        t.setTaskName(dto.getTaskName());
        if (dto.getDescription() != null)     t.setDescription(dto.getDescription());
        if (dto.getExpectedManDays() != null) t.setExpectedManDays(dto.getExpectedManDays());
        if (dto.getActualManDays() != null)   t.setActualManDays(dto.getActualManDays());
        if (dto.getStatus() != null)          t.setStatus(dto.getStatus());
        if (dto.getDueDate() != null)         t.setDueDate(dto.getDueDate());

        if (dto.getLocationId() != null) {
            LocationEntity loc = locationRepository.findById(dto.getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Location not found with id " + dto.getLocationId()));
            t.setLocation(loc);
        }

        // labors: if null -> ignore; if empty -> clear; else -> replace with resolved set
        if (dto.getLaborIds() != null) {
            if (dto.getLaborIds().isEmpty()) {
                t.getLabors().clear();
            } else {
                Set<EmployeeEntity> labors = dto.getLaborIds().stream()
                        .map(id -> employeeRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                        "Employee (labor) not found with id " + id)))
                        .collect(Collectors.toSet());
                t.setLabors(labors);
            }
        }

        // leaders: if null -> ignore; if empty -> clear; else -> replace with resolved set
        if (dto.getLeaderIds() != null) {
            if (dto.getLeaderIds().isEmpty()) {
                t.getLeaders().clear();
            } else {
                Set<EmployeeEntity> leaders = dto.getLeaderIds().stream()
                        .map(id -> employeeRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                        "Employee (leader) not found with id " + id)))
                        .collect(Collectors.toSet());
                t.setLeaders(leaders);
            }
        }
    }
}
