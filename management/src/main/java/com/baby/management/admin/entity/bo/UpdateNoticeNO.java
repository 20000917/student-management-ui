package com.baby.management.admin.entity.bo;


import lombok.Data;

@Data
public class UpdateNoticeNO {
    private Long noticeNumber;
    private String noticeTitle;
    private String noticeContent;
    private String releaseTissue;
}
