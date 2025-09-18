package com.example.hrmanagement.controller;

import com.example.hrmanagement.dto.AttendanceTypeRequest;
import com.example.hrmanagement.entity.AttendanceTypeEntity;
import com.example.hrmanagement.service.AttendanceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/attendance-types") @CrossOrigin
public class AttendanceTypeController {
    @Autowired private AttendanceTypeService service;

    @PostMapping public AttendanceTypeEntity create(@RequestBody AttendanceTypeRequest req) { return service.create(req.getName()); }
    @GetMapping  public List<AttendanceTypeEntity> all() { return service.findAll(); }

    @GetMapping("/{id}")
    public AttendanceTypeEntity get(@PathVariable Long id) { return service.get(id); }

    @PutMapping("/{id}")
    public AttendanceTypeEntity update(@PathVariable Long id, @RequestBody AttendanceTypeRequest req) {
        return service.update(id, req.getName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

