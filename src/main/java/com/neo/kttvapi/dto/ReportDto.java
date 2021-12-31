package com.neo.kttvapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {
    private Integer id;

    private String groupUserReceiveData;

    private Integer sendEmail;

    private Integer sendSms;

    private String userName;

    private String email;

    private String mobile;

    private String emailTitle;

    private String emailContent;

    private String paraName;

    private Date createdDate;

    private Integer sendFre;

    private String sendFreUnit;

    private String smsContent;

    private Integer status;
}
