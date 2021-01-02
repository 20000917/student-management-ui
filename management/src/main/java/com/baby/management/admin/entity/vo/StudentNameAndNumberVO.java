package com.baby.management.admin.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class StudentNameAndNumberVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long studentNumber;
    private String studentName;
}
