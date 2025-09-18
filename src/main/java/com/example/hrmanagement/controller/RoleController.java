package com.example.hrmanagement.controller;

import com.example.hrmanagement.dto.RoleRequest;
import com.example.hrmanagement.entity.RoleEntity;
import com.example.hrmanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/roles") @CrossOrigin
public class RoleController {
    @Autowired private RoleService service;

    @PostMapping public RoleEntity create(@RequestBody RoleRequest req) { return service.create(req.getName()); }

    @GetMapping  public List<RoleEntity> all() { return service.findAll(); }

    @GetMapping("/{id}")
    public RoleEntity get(@PathVariable Long id) { return service.get(id); }

    @PutMapping("/{id}")
    public RoleEntity update(@PathVariable Long id, @RequestBody RoleRequest req) {
        return service.update(id, req.getName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
