package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public void persist(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()) throw new IllegalStateException("email taken");
        studentRepository.save(student);
    }

    public void delete(Long id) {
        boolean exists = studentRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException("student doesn't exist");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void modify(Long id, String name, String email, LocalDate dob) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("student doesn't exist"));
        if(name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if(email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
            student.setEmail(email);
        }
        if(dob != null && !student.getDob().equals(dob)) {
            student.setDob(dob);
        }

    }
}
