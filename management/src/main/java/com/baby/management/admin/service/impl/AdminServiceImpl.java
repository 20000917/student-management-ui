package com.baby.management.admin.service.impl;

import cn.ajiehome.common.enums.CodeType;
import cn.ajiehome.common.exception.ApplicationException;
import cn.ajiehome.common.exception.entity.bo.ResultBO;
import cn.ajiehome.common.jwt.JwtUtils;
import cn.ajiehome.common.jwt.bo.JwtBeanBO;
import cn.ajiehome.common.utils.SnowFlake;
import com.baby.management.admin.entity.Admin;
import com.baby.management.admin.entity.Notice;
import com.baby.management.admin.entity.bo.*;
import com.baby.management.admin.entity.vo.AdminInfoVO;
import com.baby.management.admin.entity.vo.FacultyAndMajorVO;
import com.baby.management.admin.mapper.AdminMapper;
import com.baby.management.admin.service.IAdminService;
import com.baby.management.student.entity.Student;
import com.baby.management.student.mapper.StudentMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public ResultBO adminLogin(AdminLoginBO adminLoginBO) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getAdminNumber, adminLoginBO.getAdminNumber());
        Admin admin = baseMapper.selectOne(queryWrapper);
        if (admin == null) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "账户或者密码错误");
        } else {
            JwtBeanBO jwtBeanBO = new JwtBeanBO();
            jwtBeanBO.setId(admin.getAdminNumber());
            jwtBeanBO.setUserName(admin.getAdminName());
            return ResultBO.newResultBO(CodeType.OK, jwtUtils.getToken(24 * 60 * 60 * 1000, jwtBeanBO));
        }
    }

    @Override
    public ResultBO selectStudentAll() {
        verificationAdmin();
        JwtBeanBO jwtBeanBO = jwtUtils.getJwtBeanBO();
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getAdminNumber, jwtBeanBO.getId());
        Admin admin = baseMapper.selectOne(queryWrapper);

        List<StudentInfoBO> studentList = null;

        if (admin.getAdminRank() > 1) {
            studentList = baseMapper.selectAdminAllClass();
        } else {
            return ResultBO.newResultBO(CodeType.OK, studentList);
        }
//        switch (Integer.valueOf(admin.getAdminRank())) {
//            case 1:
//                ///班级管理员
//                studentList = baseMapper.selectAdminClass(admin);
//                break;
//            case 2:
//                studentList = baseMapper.selectAdminMajorClass(admin);
//                // 教师
//                break;
//            case 3:
//                studentList = baseMapper.selectAdminFacultyClass(admin);
//                //普通管理
//                break;
//            case 4:
//                System.err.println("执行");
//                studentList = baseMapper.selectAdminAllClass();
//                //超级管理员
//                break;
//        }
        if (studentList == null) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "学生信息获取失败");
        } else {
            return ResultBO.newResultBO(CodeType.OK, studentList);
        }
    }

    @Override
    public ResultBO selectFacultyInfo() {
        verificationAdmin();
        List<FacultyAndMajorVO> facultyAndMajorVOS = baseMapper.selectFacultyInfo(null);
        if (facultyAndMajorVOS == null) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "查询失败");
        } else {
            return ResultBO.newResultBO(CodeType.OK, facultyAndMajorVOS);
        }
    }

    @Override
    public ResultBO updateStudent(StudentUpdateBO studentUpdateBO) {
        verificationAdmin();
        System.out.println(studentUpdateBO);
        Integer integer = baseMapper.updateStudent(studentUpdateBO);
        System.out.println(integer);
        if (integer < 1) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "修改信息失败");
        } else {
            return ResultBO.newResultBO(CodeType.OK, "修改信息成功");
        }
    }

    @Override
    public ResultBO adminSelectMe() {
        JwtBeanBO jwtBeanBO = jwtUtils.getJwtBeanBO();
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getAdminNumber, jwtBeanBO.getId());
        Admin admin = baseMapper.selectOne(queryWrapper);
        if (admin == null) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "信息获取失败");
        } else {
            AdminInfoVO adminInfoVO = new AdminInfoVO();
            BeanUtils.copyProperties(admin, adminInfoVO);
            return ResultBO.newResultBO(CodeType.OK, adminInfoVO);
        }
    }

    @Override
    public ResultBO selectAllStudent() {
        verificationAdmin();
        List<FacultyAndMajorVO> facultyAndMajorVOS = baseMapper.selectAllStudent(null);
        if (facultyAndMajorVOS == null) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "查询失败");
        } else {
            return ResultBO.newResultBO(CodeType.OK, facultyAndMajorVOS);
        }
    }

    @Override
    public ResultBO selectAllManageNotice() {

        JwtBeanBO jwtBeanBO = jwtUtils.getJwtBeanBO();
        assert jwtBeanBO != null;

        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getAdminNumber, jwtBeanBO.getId());
        Admin admin = baseMapper.selectOne(queryWrapper);

        if (admin == null) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "管理员信息不存在");
        }

        List<AllManageNoticeBO> list = baseMapper.selectAllManageNotice(admin);
        if (list == null) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "查询失败");
        } else {
            return ResultBO.newResultBO(CodeType.OK, list);
        }
    }

    @Override
    public ResultBO updateAdminNotice(UpdateNoticeNO updateNoticeNO) {
        JwtBeanBO jwtBeanBO = jwtUtils.getJwtBeanBO();
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getAdminNumber, jwtBeanBO.getId());
        Admin admin = baseMapper.selectOne(queryWrapper);
        if (admin == null) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "管理员信息获取失败，请登录重试");
        }
        Integer integer = baseMapper.verifyAdminNotice(updateNoticeNO.getNoticeNumber(), admin.getAdminRank(), admin.getAdminNumber());
        if (integer == null) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "您无权操作此通知");
        }
        Notice notice = new Notice();
        BeanUtils.copyProperties(updateNoticeNO, notice);
        notice.setCreateTime(new Date(System.currentTimeMillis()));
        Integer integer1 = baseMapper.adminModifyNotice(notice);
        if (integer < 1) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "修改失败");
        } else {
            return ResultBO.newResultBO(CodeType.OK, "修改成功");
        }
    }

    @Override
    public ResultBO deleteAdminNotice(Long noticeNumber) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getAdminNumber, jwtUtils.getJwtBeanBO().getId());
        Admin admin = baseMapper.selectOne(queryWrapper);
        if (admin == null) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "管理员信息获取失败，请登录重试");
        }

        Integer integer = baseMapper.verifyAdminNotice(noticeNumber, admin.getAdminRank(), admin.getAdminNumber());
        if (integer == null) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "您无权操作此通知");
        }
        Integer integer1 = baseMapper.deleteNotice(noticeNumber);
        if (integer < 1) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "删除失败");
        } else {
            return ResultBO.newResultBO(CodeType.OK, "删除成功");
        }
    }

    @Override
    public ResultBO registerUser(RegisterStudentBO registerStudentBO) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getAdminNumber,jwtUtils.getJwtBeanBO().getId());
        Admin admin = baseMapper.selectOne(wrapper);
        if (admin == null) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "管理员信息获取失败，请登录重试");
        }

        if (admin.getAdminRank() <=1 ) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "您无权执行此操作");
        }
        Student student = new Student();
        BeanUtils.copyProperties(registerStudentBO, student);
        student.setStudentNumber(snowFlake.nextId());

        Integer integer = baseMapper.selectIdInNumberSquad(registerStudentBO.getSquadNumber());
        if (integer ==null ) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "添加失败请重试");
        }
        student.setSquadId(integer);

        int insert = studentMapper.insert(student);
        if (insert < 1) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "注册失败");
        } else {
            return ResultBO.newResultBO(CodeType.OK, "注册成功");
        }
    }

    @Override
    public ResultBO addAdminNotice(ReleaseNoticeNO releaseNoticeNO) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getAdminNumber,jwtUtils.getJwtBeanBO().getId());
        Admin admin = baseMapper.selectOne(wrapper);
        if (admin == null) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "管理员信息获取失败，请登录重试");
        }
        LambdaQueryWrapper<Student> studentWrapper = new LambdaQueryWrapper<>();
        studentWrapper.eq(Student::getStudentNumber,releaseNoticeNO.getStudentNumber());
        Student student = studentMapper.selectOne(studentWrapper);
        if (student == null) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "管理员信息学生获取失败，请重试");
        }

        Notice notice = new Notice();
        BeanUtils.copyProperties(releaseNoticeNO,notice);
        notice.setStudentId(student.getId());
        notice.setNoticeNumber(snowFlake.nextId());

        Integer integer = baseMapper.insertNotice(notice);

        if (integer < 1) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "发布失败");
        } else {
            return ResultBO.newResultBO(CodeType.OK, "发布成功");
        }
    }

    public void verificationAdmin() {
        JwtBeanBO jwtBeanBO = jwtUtils.getJwtBeanBO();
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getAdminNumber, jwtBeanBO.getId());
        Admin admin = baseMapper.selectOne(queryWrapper);
        if (admin == null) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "您非管理员");
        }
    }
}
