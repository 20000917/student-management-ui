package com.baby.management.admin.entity.bo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class AllManageNoticeBO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long noticeNumber;
    private Date createTime;
    private Short noticeType;
    private String noticeTitle;
    private String noticeContent;
    private String releaseTissue;
    private String studentName;
    private String squadName;
    private String majorName;
    private String facultyName;
}
