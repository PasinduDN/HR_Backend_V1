package com.example.hrmanagement.controller;

import com.example.hrmanagement.dto.LocationRequest;
import com.example.hrmanagement.entity.LocationEntity;
import com.example.hrmanagement.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/locations") @CrossOrigin
public class LocationController {
    @Autowired private LocationService service;

    @PostMapping public LocationEntity create(@RequestBody LocationRequest req) { return service.create(req.getName()); }
    @GetMapping  public List<LocationEntity> all() { return service.findAll(); }

    @GetMapping("/{id}")
    public LocationEntity get(@PathVariable Long id) { return service.get(id); }

    @PutMapping("/{id}")
    public LocationEntity update(@PathVariable Long id, @RequestBody LocationRequest req) {
        return service.update(id, req.getName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

