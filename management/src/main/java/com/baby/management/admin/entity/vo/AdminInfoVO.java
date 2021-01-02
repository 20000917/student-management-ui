package com.baby.management.admin.entity.vo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class AdminInfoVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long adminNumber;
    private String adminName;
    private String password;
    private Short adminSex;
    private String adminPosition;
    private Short adminRank;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long typeNumber;
}
