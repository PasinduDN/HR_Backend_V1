package com.example.hrmanagement.repository;

import com.example.hrmanagement.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {
    List<AttendanceEntity> findByEmployee_Id(Long employeeId);
    List<AttendanceEntity> findByEmployee_IdAndDate(Long employeeId, LocalDate date);
}
