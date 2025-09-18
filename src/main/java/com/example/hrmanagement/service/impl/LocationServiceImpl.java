package com.example.hrmanagement.service.impl;

import com.example.hrmanagement.entity.LocationEntity;
import com.example.hrmanagement.exception.BadRequestException;
import com.example.hrmanagement.exception.ResourceNotFoundException;
import com.example.hrmanagement.repository.LocationRepository;
import com.example.hrmanagement.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired private LocationRepository repo;

    @Override
    public LocationEntity create(String name) {
        if (repo.existsByName(name)) throw new BadRequestException("Location exists");
        return repo.save(new LocationEntity(null, name));
    }

    @Override
    public LocationEntity get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id " + id));
    }

    @Override
    public LocationEntity update(Long id, String name) {
        LocationEntity current = get(id);
        repo.findByName(name).ifPresent(other -> {
            if (!other.getId().equals(id)) throw new BadRequestException("Location exists");
        });
        current.setName(name);
        return repo.save(current);
    }

    @Override
    public void delete(Long id) {
        repo.delete(get(id));
    }

    @Override
    public List<LocationEntity> findAll() { return repo.findAll(); }
}
