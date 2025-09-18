package com.example.hrmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attendance_types", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Data @NoArgsConstructor @AllArgsConstructor
public class AttendanceTypeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name; // e.g. "Present", "Absent", "Sick Leave", "WFH"
}

