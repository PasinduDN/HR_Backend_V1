package com.example.hrmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "locations", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Data @NoArgsConstructor @AllArgsConstructor
public class LocationEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name; // e.g. "Head Office", "Warehouse A", "Site-42"
}

