package com.neo.kttvapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EMAIL_SMS")
public class EmailSms {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "EMAIL_TITLE")
    private String emailTitle;

    @Column(name = "EMAIL_CONTENT")
    private String emailContent;

    @Column(name = "SMS_CONTENT")
    private String smsContent;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "STATUS")
    private int status;
}
