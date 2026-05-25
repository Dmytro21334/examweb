package com.example.examweb.controller;

import com.example.examweb.model.Department;
import com.example.examweb.service.UniversityService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final UniversityService service;

    public DepartmentController(UniversityService service) { this.service = service; }

    @GetMapping
    public List<Department> getAll() { return service.getAllDepartments(); }

    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id) { return service.getDepartmentById(id); }

    @PostMapping
    public Department create(@RequestBody Department department) { return service.saveDepartment(department); }

    @PutMapping("/{id}")
    public Department update(@PathVariable Long id, @RequestBody Department d) {
        Department existing = service.getDepartmentById(id);
        if (existing != null) {
            existing.setName(d.getName());
            existing.setBuilding(d.getBuilding());
            existing.setPhone(d.getPhone());
            return service.saveDepartment(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.deleteDepartment(id); }
}