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
@Table(name = "SEND_FREQUENCY")
public class SendFrequency {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "CONFIG_ID")
    private Integer configId;

    @Column(name = "TYPE_REPORT")
    private String typeReport;

    @Column(name = "REPORT_ID")
    private Integer reportId;

    @Column(name = "SEND_FRE")
    private Integer sendFre;

    @Column(name = "SEND_FRE_UNIT")
    private String sendFreUnit;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @Column(name = "UPDATED_BY")
    private String updatedBy;
}
