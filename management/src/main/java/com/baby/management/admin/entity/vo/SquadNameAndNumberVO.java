package com.baby.management.admin.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class SquadNameAndNumberVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long squadNumber;
    private String squadName;
}
