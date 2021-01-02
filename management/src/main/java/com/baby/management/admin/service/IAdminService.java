package com.baby.management.admin.service;

import cn.ajiehome.common.exception.entity.bo.ResultBO;
import com.baby.management.admin.entity.bo.*;

public interface IAdminService {
    /**
     * 管理员登录
     * @param adminLoginBO 管理员信息
     * @return 结果
     */
    ResultBO adminLogin(AdminLoginBO adminLoginBO);

    /**
     * 查询所有的学生
     * @return
     */
    ResultBO selectStudentAll();

    /**
     * 查询寻所有的学院
     * @return
     */
    ResultBO selectFacultyInfo();

    /**
     * 修改学生信息
     * @param studentUpdateBO
     * @return
     */
    ResultBO updateStudent(StudentUpdateBO studentUpdateBO);

    /**
     * 查询自己的信息
     * @return
     */
    ResultBO adminSelectMe();

    /**
     * 查询所有学生以及构建的学院关系
     * @return
     */
    ResultBO selectAllStudent();

    /**
     * 查询管理员所能管理的所有信息
     * @return
     */
    ResultBO selectAllManageNotice();

    /**
     * 修改通知
     * @param updateNoticeNO
     * @return
     */
    ResultBO updateAdminNotice(UpdateNoticeNO updateNoticeNO);

    /**
     * 删除指定的通知
     * @param noticeNumber 通知ID
     * @return 结果
     */
    ResultBO deleteAdminNotice(Long noticeNumber);

    /**
     * 注册学生用户
     * @param registerStudentBO 学生信息
     * @return 结果
     */
    ResultBO registerUser(RegisterStudentBO registerStudentBO);

    /**
     * 发布通知
     * @param releaseNoticeNO
     * @return
     */
    ResultBO addAdminNotice(ReleaseNoticeNO releaseNoticeNO);
}
