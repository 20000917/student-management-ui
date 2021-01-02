package com.baby.management.student.entity.bo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class StudentNoticeBO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long noticeNumber;
    private String noticeTitle;
    private String noticeContent;
    private Date createTime;
    private String releaseTissue;
}
