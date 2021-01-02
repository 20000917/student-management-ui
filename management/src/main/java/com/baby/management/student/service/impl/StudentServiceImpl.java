package com.baby.management.student.service.impl;


import cn.ajiehome.common.enums.CodeType;
import cn.ajiehome.common.exception.ApplicationException;
import cn.ajiehome.common.exception.entity.bo.ResultBO;
import cn.ajiehome.common.jwt.JwtUtils;
import cn.ajiehome.common.jwt.bo.JwtBeanBO;
import com.baby.management.student.entity.Student;
import com.baby.management.student.entity.bo.StudentLoginBO;
import com.baby.management.student.entity.bo.StudentModifyPasswordBO;
import com.baby.management.student.entity.bo.StudentNoticeBO;
import com.baby.management.student.entity.vo.StudentInfoVO;
import com.baby.management.student.mapper.StudentMapper;
import com.baby.management.student.service.IStudentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public ResultBO login(StudentLoginBO studentLoginBO) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getStudentNumber,studentLoginBO.getStudentNumber()).eq(Student::getPassword,studentLoginBO.getPassword());
        Student student = baseMapper.selectOne(queryWrapper);
        if (student==null){
            throw new ApplicationException(CodeType.SERVICE_ERROR, "用户信息错误");
        }else {
            JwtBeanBO jwtBeanBO = new JwtBeanBO();
            jwtBeanBO.setId(student.getStudentNumber());
            jwtBeanBO.setUserName(student.getStudentName());
            return ResultBO.newResultBO(CodeType.OK,this.jwtUtils.getToken(24*60*60*1000,jwtBeanBO));
        }
    }

    @Override
    public ResultBO selectMeInfo() {
        JwtBeanBO jwtBeanBO = jwtUtils.getJwtBeanBO();
        assert jwtBeanBO != null;
        Long studentNumber = jwtBeanBO.getId();
        StudentInfoVO studentInfoVO = baseMapper.selectMeInfo(studentNumber);
        if (studentInfoVO==null){
            throw new ApplicationException(CodeType.SERVICE_ERROR, "查询失败请重试");
        }else {
            return ResultBO.newResultBO(CodeType.OK, studentInfoVO);
        }
    }

    @Override
    public ResultBO selectMeNotice() {
        JwtBeanBO jwtBeanBO = jwtUtils.getJwtBeanBO();
        List<StudentNoticeBO> studentNoticeBOS = baseMapper.selectMeNotice(jwtBeanBO.getId());
        if (studentNoticeBOS==null){
            throw new ApplicationException(CodeType.SERVICE_ERROR, "数据创建错误");
        }else {
            return ResultBO.newResultBO(CodeType.OK,studentNoticeBOS);
        }
    }

    @Override
    public ResultBO modifyPassword(StudentModifyPasswordBO studentModifyPasswordBO) {
        JwtBeanBO jwtBeanBO = jwtUtils.getJwtBeanBO();
        if (!jwtBeanBO.getId().equals(studentModifyPasswordBO.getStudentNumber())){
            throw new ApplicationException(CodeType.SERVICE_ERROR, "您只能修改自己的信息");
        }else {
            Student student = new Student();
            student.setPassword(studentModifyPasswordBO.getPassword());
            LambdaUpdateWrapper<Student> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Student::getStudentNumber,studentModifyPasswordBO.getStudentNumber());
            int update = baseMapper.update(student, updateWrapper);
            if (update<1){
                throw new ApplicationException(CodeType.SERVICE_ERROR, "密码修改失败");
            }else {
                return ResultBO.newResultBO(CodeType.OK, "密码修改成功");
            }
        }
    }
}
