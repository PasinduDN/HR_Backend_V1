package com.example.hrmanagement.controller;

import com.example.hrmanagement.dto.Task;
import com.example.hrmanagement.entity.TaskEntity;
import com.example.hrmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Create a new task (assign to an employee)
    @PostMapping
    public TaskEntity createTask(@RequestBody Task taskDTO) {
        return taskService.createTask(taskDTO);
    }

    // Get all tasks
    @GetMapping
    public List<TaskEntity> getAllTasks() {
        return taskService.getAllTasks();
    }

    // Get a specific task by ID
    @GetMapping("/{id}")
    public TaskEntity getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    // Update a task
    @PutMapping("/{id}")
    public TaskEntity updateTask(@PathVariable Long id, @RequestBody Task taskDTO) {
        return taskService.updateTask(id, taskDTO);
    }

    // Delete a task
    @DeleteMapping("/{id}")
    public Map<String, String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return Collections.singletonMap("status", "Task deleted successfully");
    }
}

