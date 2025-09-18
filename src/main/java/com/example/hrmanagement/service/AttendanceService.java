package com.example.hrmanagement.service;

import com.example.hrmanagement.dto.Attendance;
import com.example.hrmanagement.entity.AttendanceEntity;
import java.util.List;

public interface AttendanceService {
    AttendanceEntity createAttendance(Attendance attendanceDTO);
    AttendanceEntity getAttendanceById(Long id);
    List<AttendanceEntity> getAllAttendances();
    AttendanceEntity updateAttendance(Long id, Attendance attendanceDTO);
    void deleteAttendance(Long id);
    List<AttendanceEntity> getAttendanceByEmployee(Long employeeId);
}
