package com.sda.student_nodb.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
public class Student {
    private Long id;
    private String name;
    private String email;
    private String gender;
    private String address;
    private String phoneNumber;
}
