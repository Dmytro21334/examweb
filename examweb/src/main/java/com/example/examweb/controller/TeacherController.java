package com.example.examweb.controller;

import com.example.examweb.model.Teacher;
import com.example.examweb.service.UniversityService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final UniversityService service;

    public TeacherController(UniversityService service) { this.service = service; }

    @GetMapping
    public List<Teacher> getAll() { return service.getAllTeachers(); }

    @GetMapping("/{id}")
    public Teacher getById(@PathVariable Long id) { return service.getTeacherById(id); }

    @PostMapping
    public Teacher create(@RequestBody Teacher teacher) { return service.saveTeacher(teacher); }

    @PutMapping("/{id}")
    public Teacher update(@PathVariable Long id, @RequestBody Teacher t) {
        Teacher existing = service.getTeacherById(id);
        if (existing != null) {
            existing.setFirstName(t.getFirstName());
            existing.setLastName(t.getLastName());
            existing.setEmail(t.getEmail());
            existing.setPosition(t.getPosition());
            return service.saveTeacher(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.deleteTeacher(id); }

    @PutMapping("/{teacherId}/assign/{departmentId}")
    public Teacher assignToDepartment(@PathVariable Long teacherId, @PathVariable Long departmentId) {
        return service.assignTeacherToDepartment(teacherId, departmentId);
    }
}