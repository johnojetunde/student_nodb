package com.sda.student_nodb.repository;

import com.sda.student_nodb.model.Student;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Profile("mysql")
@Repository
public class MysqlRepository implements StudentRepository{
    @Override
    public Optional<Student> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Collection<Student> findAll() {
        return null;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Student save(Student student) {
        return null;
    }

    @Override
    public Student update(Student student, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public String databaseName() {
        return "MySQL";
    }
}
