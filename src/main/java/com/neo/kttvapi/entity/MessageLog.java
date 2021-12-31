package com.neo.kttvapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MESSAGE")
public class MessageLog {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "SENDER")
    private String sender;

    @Column(name = "RECEIVER")
    private String receiver;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "TIME_SEND")
    private Timestamp timeSend;

    @Column(name = "TIME_FINISH")
    private Timestamp timeFinish;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "NOTE")
    private String note;
}
