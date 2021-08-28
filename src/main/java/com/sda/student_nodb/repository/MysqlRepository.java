package com.sda.student_nodb.repository;

import com.sda.student_nodb.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.*;
import static org.springframework.data.domain.Sort.Order.*;

@Profile("mysql")
@Repository
@RequiredArgsConstructor
public class MysqlRepository implements StudentDBRepository {

    private final StudentRepository studentRepository;

    @Override
    public Optional<Student> findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public Collection<Student> findAll() {
        return studentRepository.findAll(Sort.by("gender").descending());
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student, Long id) {
        var existingStudent = findById(id)
                .orElseThrow(() -> new RuntimeException("Unable to find student with id " + id));

        existingStudent.setName(student.getName());
        existingStudent.setAddress(student.getAddress());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPhoneNumber(student.getPhoneNumber());
        existingStudent.setGender(student.getGender());

        return studentRepository.save(existingStudent);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public String databaseName() {
        return "MySQL";
    }
}
