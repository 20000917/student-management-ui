package com.baby.management.admin.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

@Data
public class SquadAndStudentVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long squadNumber;
    private String squadName;
    private List<StudentNameAndNumberVO> studentList;
}
