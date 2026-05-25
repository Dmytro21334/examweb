package com.example.examweb.service;

import com.example.examweb.model.Department;
import com.example.examweb.model.Teacher;
import com.example.examweb.repository.DepartmentRepository;
import com.example.examweb.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UniversityService {

    private final DepartmentRepository departmentRepository;
    private final TeacherRepository teacherRepository;

    public UniversityService(DepartmentRepository departmentRepository, TeacherRepository teacherRepository) {
        this.departmentRepository = departmentRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<Department> getAllDepartments() { return departmentRepository.findAll(); }
    public Department getDepartmentById(Long id) { return departmentRepository.findById(id).orElse(null); }
    public Department saveDepartment(Department d) { return departmentRepository.save(d); }
    public void deleteDepartment(Long id) { departmentRepository.deleteById(id); }

    public List<Teacher> getAllTeachers() { return teacherRepository.findAll(); }
    public Teacher getTeacherById(Long id) { return teacherRepository.findById(id).orElse(null); }
    public Teacher saveTeacher(Teacher t) { return teacherRepository.save(t); }
    public void deleteTeacher(Long id) { teacherRepository.deleteById(id); }

    public Teacher assignTeacherToDepartment(Long teacherId, Long departmentId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        Department department = departmentRepository.findById(departmentId).orElse(null);

        if (teacher != null && department != null) {
            teacher.setDepartment(department);
            return teacherRepository.save(teacher);
        }
        return null;
    }
}