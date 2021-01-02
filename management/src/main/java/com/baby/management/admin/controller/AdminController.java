package com.baby.management.admin.controller;

import cn.ajiehome.common.annotations.AllowToken;
import cn.ajiehome.common.exception.entity.bo.ResultBO;
import com.baby.management.admin.entity.bo.*;
import com.baby.management.admin.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    @PostMapping("/login")
    public ResultBO adminLogin(@RequestBody AdminLoginBO adminLoginBO){
        return adminService.adminLogin(adminLoginBO);
    }

    @GetMapping("/select/me")
    @AllowToken
    public ResultBO adminSelectMe(){
        return adminService.adminSelectMe();
    }

    @GetMapping("/select/student/all")
    @AllowToken
    public ResultBO selectStudentAll(){
        return adminService.selectStudentAll();
    }

    @GetMapping("/select/faculty/info")
    @AllowToken
    public ResultBO selectFacultyInfo(){
        return adminService.selectFacultyInfo();
    }

    @PostMapping("/update/student")
    @AllowToken
    public ResultBO updateStudent(@RequestBody  StudentUpdateBO studentUpdateBO){
        return adminService.updateStudent(studentUpdateBO);
    }
    @GetMapping("/select/all/student")
    @AllowToken
    public ResultBO selectAllStudent(){
        return adminService.selectAllStudent();
    }

    @GetMapping("/select/all/manage/notice")
    @AllowToken
    public ResultBO selectAllManageNotice(){
        return adminService.selectAllManageNotice();
    }

    @PostMapping("/update/notice")
    @AllowToken
    public ResultBO updateAdminNotice(@RequestBody UpdateNoticeNO updateNoticeNO){
        return adminService.updateAdminNotice(updateNoticeNO);
    }

    @GetMapping("/delete/notice/{noticeNumber}")
    @AllowToken
    public ResultBO deleteAdminNotice(@PathVariable("noticeNumber")Long noticeNumber){
        return adminService.deleteAdminNotice(noticeNumber);
    }
    @PostMapping("/register/user")
    @AllowToken
    public ResultBO registerUser(@RequestBody RegisterStudentBO registerStudentBO){
        return adminService.registerUser(registerStudentBO);
    }

    @PostMapping("/add/notice")
    @AllowToken
    public ResultBO addAdminNotice(@RequestBody ReleaseNoticeNO releaseNoticeNO){
        return adminService.addAdminNotice(releaseNoticeNO);
    }
}
