package com.example.hrmanagement.controller;

import com.example.hrmanagement.dto.Employee;
import com.example.hrmanagement.entity.EmployeeEntity;
import com.example.hrmanagement.entity.TaskEntity;
import com.example.hrmanagement.entity.AttendanceEntity;
import com.example.hrmanagement.service.EmployeeService;
import com.example.hrmanagement.service.TaskService;
import com.example.hrmanagement.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/employees")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private AttendanceService attendanceService;

    // Create a new employee
    @PostMapping
    public EmployeeEntity createEmployee(@RequestBody Employee employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    // Get all employees
    @GetMapping
    public List<EmployeeEntity> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Get a specific employee by ID
    @GetMapping("/{id}")
    public EmployeeEntity getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    // Update an existing employee
    @PutMapping("/{id}")
    public EmployeeEntity updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDTO) {
        return employeeService.updateEmployee(id, employeeDTO);
    }

    // Delete an employee
    @DeleteMapping("/{id}")
    public Map<String, String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return Collections.singletonMap("status", "Employee deleted successfully");
    }

    // Additional endpoints to get an employee's tasks and attendance records:

    // Get all tasks for a specific employee
    @GetMapping("/{id}/tasks")
    public List<TaskEntity> getTasksByEmployee(@PathVariable Long id) {
        return taskService.getTasksByEmployee(id);
    }

    // Get all attendance records for a specific employee
    @GetMapping("/{id}/attendances")
    public List<AttendanceEntity> getAttendanceByEmployee(@PathVariable Long id) {
        return attendanceService.getAttendanceByEmployee(id);
    }
}

