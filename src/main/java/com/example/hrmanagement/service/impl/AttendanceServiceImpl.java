package com.example.hrmanagement.service.impl;

import com.example.hrmanagement.dto.Attendance;
import com.example.hrmanagement.entity.AttendanceEntity;
import com.example.hrmanagement.entity.EmployeeEntity;
import com.example.hrmanagement.exception.ResourceNotFoundException;
import com.example.hrmanagement.repository.AttendanceRepository;
import com.example.hrmanagement.repository.AttendanceTypeRepository;
import com.example.hrmanagement.repository.EmployeeRepository;
import com.example.hrmanagement.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// imports omitted
@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired private AttendanceRepository attendanceRepository;
    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private AttendanceTypeRepository attendanceTypeRepository;

    @Override
    public AttendanceEntity createAttendance(Attendance dto) {
        AttendanceEntity a = new AttendanceEntity();
        map(a, dto);
        return attendanceRepository.save(a);
    }

    @Override
    public AttendanceEntity getAttendanceById(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found with id " + id));
    }

    @Override
    public List<AttendanceEntity> getAllAttendances() { return attendanceRepository.findAll(); }

    @Override
    public AttendanceEntity updateAttendance(Long id, Attendance dto) {
        AttendanceEntity a = getAttendanceById(id);
        map(a, dto);
        return attendanceRepository.save(a);
    }

    @Override
    public void deleteAttendance(Long id) {
        attendanceRepository.delete(getAttendanceById(id));
    }

    @Override
    public List<AttendanceEntity> getAttendanceByEmployee(Long employeeId) {
        if (!employeeRepository.existsById(employeeId))
            throw new ResourceNotFoundException("Employee not found with id " + employeeId);
        return attendanceRepository.findByEmployee_Id(employeeId);
    }

    private void map(AttendanceEntity a, Attendance dto) {
        if (dto.getDate()!=null)        a.setDate(dto.getDate());
        if (dto.getCheckInTime()!=null) a.setCheckInTime(dto.getCheckInTime());
        if (dto.getCheckOutTime()!=null)a.setCheckOutTime(dto.getCheckOutTime());
        if (dto.getEmployeeId()!=null) {
            a.setEmployee(employeeRepository.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + dto.getEmployeeId())));
        }
        if (dto.getAttendanceTypeId()!=null) {
            a.setAttendanceType(attendanceTypeRepository.findById(dto.getAttendanceTypeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Attendance type not found with id " + dto.getAttendanceTypeId())));
        }
    }
}

