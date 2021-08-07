package com.sda.student_nodb.service;

import com.sda.student_nodb.model.Student;
import com.sda.student_nodb.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;

    public Student registerStudent(Student student) {
        var existingStudent = repository.findByEmail(student.getEmail());
        if (existingStudent.isPresent()) {
            System.out.printf("\u001B[31m" + " Student with email %s \u001B[0m %n", student.getEmail());
        }

        return repository.save(student);
    }

    public void removeStudent(Long id) {
        repository.delete(id);
    }

    public Collection<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(Long id) {
        var studentOptional = repository.findById(id);

        if (studentOptional.isPresent()) {
            return studentOptional.get();
        }

        System.out.printf("\u001B[31m" + " Student with id %d  does not exist \u001B[0m %n", id);
        return null;
    }
}
