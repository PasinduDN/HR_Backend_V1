package com.example.hrmanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "attendances",
        uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id","date"})) // prevent duplicate same-day entries
@Data @NoArgsConstructor @AllArgsConstructor
public class AttendanceEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime checkInTime;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime checkOutTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attendance_type_id")
    private AttendanceTypeEntity attendanceType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;
}
