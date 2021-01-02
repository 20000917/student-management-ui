package com.baby.management.admin.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Notice {
    private Integer id;
    private Long noticeNumber;
    private String noticeTitle;
    private String noticeContent;
    private Short noticeType;
    private Date createTime;
    private String releaseTissue;
    private Integer studentId;
}
