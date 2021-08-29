package com.sda.student_nodb.repository;

import com.sda.student_nodb.model.Student;
import com.sda.student_nodb.model.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
    @Query("select s.student from StudentCourse  s where s.course.courseName = :courseName")
    List<Student> findStudentByCourseName(@Param("courseName") String courseName);

    List<StudentCourse> findAllByCourse_CourseName(String courseName);
}
