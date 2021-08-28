package com.sda.student_nodb.repository;

import com.sda.student_nodb.model.Student;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Profile("hashmap")
@Repository
public class HashMapStudentRepository implements StudentDBRepository {
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
    public Student update(Student student, Long id) {
        var existingStudent = findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Student with id %d does not exist", id)));

        existingStudent
                .setName(student.getName())
                .setAddress(student.getAddress())
                .setEmail(student.getEmail())
                .setGender(student.getGender())
                .setPhoneNumber(student.getPhoneNumber());

        MAP_DB.put(id, existingStudent);

        return existingStudent;
    }

    @Override
    public void delete(Long id) {
        MAP_DB.remove(id);
    }

    @Override
    public String databaseName() {
        return "HashMap";
    }
}
