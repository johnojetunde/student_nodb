package com.sda.student_nodb.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@ToString
@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "Gender is required")
    private String gender;
    @NotBlank(message = "address is required")
    private String address;
    @NotBlank
    @Size(min = 11, max = 13, message = "Phone number should be a minimum of 11 characters and maximum of 13 characters")
    private String phoneNumber;

    @ManyToMany
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(
                    name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;
}
