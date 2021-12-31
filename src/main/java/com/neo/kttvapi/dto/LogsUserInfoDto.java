package com.neo.kttvapi.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LogsUserInfoDto {
    private long id;
    private long userInfoId;
    private String name;
    private String userName;
    private String mobile;
    private String position;
    private String email;
    private Integer gender;
    private Integer statusId;
    private Integer checkRole;
    private String dateRole;
    private String cardNumber;
    private String code;
    private Integer officeCode;
    private Integer isDelete;
    private String unit;
    private String groupPrivilegeList;
    private Instant dateOfBirth;
    private String roleGroups;
    private Instant createdDate;
    private Instant modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private String damLakeAndStation;
    private String damLakes;
    private String stations;

}
