package com.baby.management.student.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("student")
public class Student extends Model<Student> {
    @TableId(value = "id",type = IdType.INPUT)
    private Integer id;
    private Integer squadId;
    private Long studentNumber;
    private String studentName;
    private Short studentSex;
    private String password;
}
