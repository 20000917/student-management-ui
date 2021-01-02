package com.baby.management.student.mapper;

import com.baby.management.student.entity.Student;
import com.baby.management.student.entity.bo.StudentNoticeBO;
import com.baby.management.student.entity.vo.StudentInfoVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentMapper extends BaseMapper<Student> {
    /**
     * 查询用户信息
     * @param studentNumber 学生编号
     * @return 结果
     */
    StudentInfoVO selectMeInfo(@Param("studentNumber") Long studentNumber);

    /**
     * 查询学生的奖惩记录
     * @param studentNumber 学号
     * @return 结果
     */
    List<StudentNoticeBO> selectMeNotice(@Param("studentNumber")Long studentNumber);


}
