package com.example.hrmanagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
    private LocalDate date;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private Long attendanceTypeId;
    private Long employeeId;
}
