package com.baby.management.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin")
public class Admin {
    @TableId(value = "id",type = IdType.INPUT)
    private Integer id;

    private Long adminNumber;
    private String adminName;
    private String password;
    private Short adminSex;
    private String adminPosition;
    private Short adminRank;
    private Long typeNumber;

}
