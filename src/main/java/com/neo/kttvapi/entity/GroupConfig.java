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
@Table(name = "GROUP_CONFIG")
public class GroupConfig {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @Column(name = "SEND_EMAIL")
    private Integer sendEmail;

    @Column(name = "SEND_SMS")
    private Integer sendSms;

    @Column(name = "SEND_STATION_DATA")
    private Integer sendStationData;

    @Column(name = "SEND_LAKE_DATA")
    private Integer sendLakeData;

    @Column(name = "STATION_LIST")
    private String stationList;

    @Column(name = "LAKE_LIST")
    private String lakeList;

    @Column(name = "STATION_REPORT_ID")
    private Integer stationReportId;

    @Column(name = "LAKE_REPORT_ID")
    private Integer lakeReportId;

    @Column(name = "GROUP_USER_RECEIVE_DATA")
    private String groupUserReceiveData;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_DATE")
    private Date updateDate;

    @Column(name = "UPDATED_BY")
    private String updateBy;

    @Column(name = "SEND_LIST")
    private long sendList;
}
