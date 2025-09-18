package com.example.hrmanagement.service;

import com.example.hrmanagement.entity.LocationEntity;
import java.util.List;

public interface LocationService {
    LocationEntity create(String name);
    LocationEntity get(Long id);
    LocationEntity update(Long id, String name);
    void delete(Long id);
    List<LocationEntity> findAll();
}
