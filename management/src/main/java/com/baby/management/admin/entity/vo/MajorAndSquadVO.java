package com.baby.management.admin.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

@Data
public class MajorAndSquadVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long majorNumber;
    private String majorName;
    private List<SquadNameAndNumberVO> squadList;
}
