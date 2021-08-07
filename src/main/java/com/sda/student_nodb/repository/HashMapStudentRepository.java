package com.sda.student_nodb.repository;

import com.sda.student_nodb.model.Student;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class HashMapStudentRepository implements StudentRepository {
    private static final Map<Long, Student> MAP_DB = new HashMap<>();
    private static Long LAST_ID = 0L;

    @Override
    public Optional<Student> findByEmail(String email) {
        return MAP_DB.values().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public Collection<Student> findAll() {
        return MAP_DB.values();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(MAP_DB.get(id));
    }

    @Override
    public Student save(Student student) {
        Long newId = LAST_ID + 1;

        student.setId(newId);

        MAP_DB.put(newId, student);
        LAST_ID = newId;

        return student;
    }

    @Override
    public void delete(Long id) {
        MAP_DB.remove(id);
    }
}
