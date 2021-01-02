package com.baby.management.admin.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

@Data
public class FacultyAndMajorVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long facultyNumber;
    private String facultyName;
    private List<MajorAndSquadVO> majorList;

}
