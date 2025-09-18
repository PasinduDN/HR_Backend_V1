package com.example.hrmanagement.service.impl;

import com.example.hrmanagement.dto.Employee;
import com.example.hrmanagement.entity.EmployeeEntity;
import com.example.hrmanagement.entity.RoleEntity;
import com.example.hrmanagement.exception.ResourceNotFoundException;
import com.example.hrmanagement.repository.EmployeeRepository;
import com.example.hrmanagement.repository.RoleRepository;
import com.example.hrmanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// imports omitted for brevity
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private RoleRepository roleRepository;

    @Override
    public EmployeeEntity createEmployee(Employee dto) {
        EmployeeEntity e = new EmployeeEntity();
        map(e, dto);
        return employeeRepository.save(e);
    }

    @Override
    public EmployeeEntity getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    @Override
    public List<EmployeeEntity> getAllEmployees() { return employeeRepository.findAll(); }

    @Override
    public EmployeeEntity updateEmployee(Long id, Employee dto) {
        EmployeeEntity e = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        map(e, dto);
        return employeeRepository.save(e);
    }

    @Override
    public void deleteEmployee(Long id) {
        EmployeeEntity e = getEmployeeById(id);
        employeeRepository.delete(e);
    }

    private void map(EmployeeEntity e, Employee dto) {
        if (dto.getFirstName()!=null) e.setFirstName(dto.getFirstName());
        if (dto.getLastName()!=null)  e.setLastName(dto.getLastName());
        if (dto.getEmail()!=null)     e.setEmail(dto.getEmail());
        if (dto.getAddress()!=null)   e.setAddress(dto.getAddress());
        if (dto.getCity()!=null)      e.setCity(dto.getCity());
        if (dto.getPhone()!=null)     e.setPhone(dto.getPhone());
        if (dto.getSkills()!=null)    e.setSkills(dto.getSkills());
        if (dto.getRoleId()!=null) {
            RoleEntity role = roleRepository.findById(dto.getRoleId())
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + dto.getRoleId()));
            e.setRole(role);
        }
    }
}

