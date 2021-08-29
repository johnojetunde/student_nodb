package com.sda.student_nodb.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "student_course")
@Entity
public class StudentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
