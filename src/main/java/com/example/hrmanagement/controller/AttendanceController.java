package com.example.hrmanagement.controller;

import com.example.hrmanagement.dto.Attendance;
import com.example.hrmanagement.entity.AttendanceEntity;
import com.example.hrmanagement.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/attendances")
@CrossOrigin
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // Create a new attendance record for an employee
    @PostMapping
    public AttendanceEntity createAttendance(@RequestBody Attendance attendanceDTO) {
        return attendanceService.createAttendance(attendanceDTO);
    }

    // Get all attendance records (optionally, this could be huge if many records)
    @GetMapping
    public List<AttendanceEntity> getAllAttendance() {
        return attendanceService.getAllAttendances();
    }

    // Get a specific attendance record by ID
    @GetMapping("/{id}")
    public AttendanceEntity getAttendanceById(@PathVariable Long id) {
        return attendanceService.getAttendanceById(id);
    }

    // Update an attendance record
    @PutMapping("/{id}")
    public AttendanceEntity updateAttendance(@PathVariable Long id, @RequestBody Attendance attendanceDTO) {
        return attendanceService.updateAttendance(id, attendanceDTO);
    }

    // Delete an attendance record
    @DeleteMapping("/{id}")
    public Map<String, String> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return Collections.singletonMap("status", "Attendance record deleted successfully");
    }
}

