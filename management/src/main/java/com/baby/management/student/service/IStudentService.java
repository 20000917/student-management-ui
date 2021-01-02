package com.baby.management.student.service;

import cn.ajiehome.common.exception.entity.bo.ResultBO;
import com.baby.management.student.entity.bo.StudentLoginBO;
import com.baby.management.student.entity.bo.StudentModifyPasswordBO;

public interface IStudentService {
    /**
     * 学生登录
     * @param studentLoginBO 登录信息
     * @return 返回信息
     */
    ResultBO login(StudentLoginBO studentLoginBO);

    /**
     * 查询自己的信息
     * @return 结果
     */
    ResultBO selectMeInfo();

    /**
     * 查询与自己有关的通知
     * @return 结果
     */
    ResultBO selectMeNotice();

    /**
     * 修改密码
     * @param studentModifyPasswordBO 学生密码
     * @return 结果
     */
    ResultBO modifyPassword(StudentModifyPasswordBO studentModifyPasswordBO);
}
