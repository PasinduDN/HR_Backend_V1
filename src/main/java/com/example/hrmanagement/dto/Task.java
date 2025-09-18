package com.example.hrmanagement.dto;

import com.example.hrmanagement.entity.TaskStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private String taskName;
    private String description;
    private Integer expectedManDays;
    private Integer actualManDays;
    private TaskStatus status;
    private LocalDate dueDate;
    private Long locationId;
    private Set<Long> laborIds;
    private Set<Long> leaderIds;
}