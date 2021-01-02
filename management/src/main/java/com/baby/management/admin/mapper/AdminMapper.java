package com.baby.management.admin.mapper;

import com.baby.management.admin.entity.Admin;
import com.baby.management.admin.entity.Notice;
import com.baby.management.admin.entity.bo.AllManageNoticeBO;
import com.baby.management.admin.entity.bo.StudentInfoBO;
import com.baby.management.admin.entity.bo.StudentUpdateBO;
import com.baby.management.admin.entity.vo.FacultyAndMajorVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 查询当前管理所管理的人员
     * @param admin 管理员信息
     * @return 结果
     */
    List<StudentInfoBO> selectAdminClass(Admin admin);

    /**
     * 专业管理员
     * @param admin
     * @return
     */
    List<StudentInfoBO> selectAdminMajorClass(Admin admin);

    /**
     * 学院管理员
     * @param admin
     * @return
     */
    List<StudentInfoBO> selectAdminFacultyClass(Admin admin);

    /**
     * 总管理
     * @return
     */
    List<StudentInfoBO> selectAdminAllClass();

    /**
     * 查询所有学院的信息
     * @param admin 管理员信息
     * @return 结果
     */
    List<FacultyAndMajorVO> selectFacultyInfo(Admin admin);

    /**
     * 修改用户信息
     * @param studentUpdateBO
     * @return
     */
    Integer updateStudent(StudentUpdateBO studentUpdateBO);


    /**
     * 查询所有学生以及构建的学院关系
     * @param admin 管理员
     * @return 结果
     */
    List<FacultyAndMajorVO> selectAllStudent(Admin admin);


    /**
     * 查询管理员所能管理的所有信息
     * @param admin
     * @return
     */
    List<AllManageNoticeBO> selectAllManageNotice(Admin admin);

    /**
     * 查询管理员是否有权限修改
     * @param noticeNumber 通知编号
     * @param adminRank 管理级别
     * @param adminNumber 管理编号
     * @return 结果
     */
    Integer verifyAdminNotice(@Param("noticeNumber") Long noticeNumber,@Param("adminRank") Short adminRank, @Param("adminNumber") Long adminNumber);

    /**
     * 修改通知
     * @param notice 通知信息
     * @return 结果
     */
    Integer adminModifyNotice(Notice notice);

    /**
     * 删除指定的奖惩
     * @param noticeNumber 结果
     * @return 结果
     */
    Integer deleteNotice(@Param("noticeNumber") Long noticeNumber );

    /**
     * 查询squadId
     * @param squadNumber
     * @return
     */
    Integer selectIdInNumberSquad(@Param("squadNumber") Long squadNumber);


    /**
     * 发布通知
     * @param notice
     * @return
     */
    Integer insertNotice(Notice notice);
}
