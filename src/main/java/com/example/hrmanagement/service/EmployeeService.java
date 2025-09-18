package com.example.hrmanagement.service;

import com.example.hrmanagement.dto.Employee;
import com.example.hrmanagement.entity.EmployeeEntity;
import java.util.List;

public interface EmployeeService {
    EmployeeEntity createEmployee(Employee employeeDTO);
    EmployeeEntity getEmployeeById(Long id);
    List<EmployeeEntity> getAllEmployees();
    EmployeeEntity updateEmployee(Long id, Employee employeeDTO);
    void deleteEmployee(Long id);
}
