package com.example.hrmanagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String city;
    private String phone;
    private Long roleId;
    private Set<String> skills;
}
