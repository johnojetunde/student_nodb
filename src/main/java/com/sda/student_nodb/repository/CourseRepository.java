package com.sda.student_nodb.repository;

import com.sda.student_nodb.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Duration;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByCourseName(String name);

    List<Course> findAllByDuration(Duration duration);
}
