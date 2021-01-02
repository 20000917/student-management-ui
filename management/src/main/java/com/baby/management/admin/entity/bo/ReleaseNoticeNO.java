package com.baby.management.admin.entity.bo;

import lombok.Data;

@Data
public class ReleaseNoticeNO {
    private String noticeTitle;
    private String noticeContent;
    private Short noticeType;
    private String releaseTissue;
    private Long studentNumber;
}
