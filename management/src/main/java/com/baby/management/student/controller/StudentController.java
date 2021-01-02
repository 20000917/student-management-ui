package com.baby.management.student.controller;

import cn.ajiehome.common.annotations.AllowToken;
import cn.ajiehome.common.exception.entity.bo.ResultBO;
import com.baby.management.student.entity.bo.StudentLoginBO;
import com.baby.management.student.entity.bo.StudentModifyPasswordBO;
import com.baby.management.student.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;


    @GetMapping("/select/me")
    @AllowToken
    public ResultBO selectMeInfo(){
        return studentService.selectMeInfo();
    }

    @PostMapping("/login")
    public ResultBO login(@RequestBody StudentLoginBO studentLoginBO){
        return studentService.login(studentLoginBO);
    }


    @GetMapping("/notice")
    @AllowToken
    public ResultBO selectMeNotice(){
        return studentService.selectMeNotice();
    }
    @PostMapping("/modify/password")
    @AllowToken
    public ResultBO modifyPassword(@RequestBody StudentModifyPasswordBO studentModifyPasswordBO){
        return studentService.modifyPassword(studentModifyPasswordBO);
    }
}
