package com.sda.student_nodb.repository;

import com.sda.student_nodb.model.Course;
import com.sda.student_nodb.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

    List<Student> findAllByName(String name);

    List<Student> findAllByCoursesIn(List<Course> courses);
}
