package com.sda.student_nodb.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString
@Builder
@Data
@Accessors(chain = true)
public class Student {
    private Long id;
    private String name;
    private String email;
    private String gender;
    private String address;
    private String phoneNumber;
}
