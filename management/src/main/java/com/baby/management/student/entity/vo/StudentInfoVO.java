package com.baby.management.student.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class StudentInfoVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long studentNumber;
    private String studentName;
    private Short studentSex;
    private String password;
    private String facultyName;
    private String majorName;
    private String squadName;
}
