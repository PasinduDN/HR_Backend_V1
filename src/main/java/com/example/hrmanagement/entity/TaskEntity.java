package com.example.hrmanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Data @NoArgsConstructor @AllArgsConstructor
public class TaskEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskName;
    private String description;

    private Integer expectedManDays; // estimate
    private Integer actualManDays;   // tracked actual

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.NOT_STARTED;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    // Many tasks can be at one location
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private LocationEntity location;

    // Many-to-many: labors assigned to this task
    @ManyToMany
    @JoinTable(
            name = "task_labors",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<EmployeeEntity> labors = new HashSet<>();

    // Many-to-many: leaders for this task
    @ManyToMany
    @JoinTable(
            name = "task_leaders",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<EmployeeEntity> leaders = new HashSet<>();
}

