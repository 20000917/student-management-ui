package com.baby.management.admin.entity.bo;

import lombok.Data;

@Data
public class StudentUpdateBO {
    private Long studentNumber;
    private String studentName;
    private Short studentSex;
    private String password;
    private Long squadNumber;
}
