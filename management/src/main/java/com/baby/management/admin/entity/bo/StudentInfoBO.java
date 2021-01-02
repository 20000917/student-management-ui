package com.baby.management.admin.entity.bo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class StudentInfoBO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long studentNumber;
    private String studentName;
    private Short studentSex;
    private String password;
    private String squadName;
    private String majorName;
    private String facultyName;
}
