package com.sda.student_nodb.repository;

import com.sda.student_nodb.model.Student;

import java.util.Collection;
import java.util.Optional;

public interface StudentRepository {
    Optional<Student> findByEmail(String email);

    Collection<Student> findAll();

    Optional<Student> findById(Long id);

    Student save(Student student);

    Student update(Student student, Long id);

    void delete(Long id);
}
