package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudent() {
        return studentService.findAll();
    }

    @PostMapping
    public void saveStudent(@RequestBody Student student) {
        studentService.persist(student);
    }

    @DeleteMapping(path = "{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void updateStudent(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) LocalDate dob
            ) {
        studentService.modify(id, name, email, dob);
    }
}
