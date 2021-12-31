package com.neo.kttvapi.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.neo.kttvapi.entity.BaseEntity;
import com.neo.kttvapi.entity.GroupPrivilege;
import com.neo.kttvapi.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserInfoDto {

    private Integer id;

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
    private String[] damLakesList;
    private String[] stationsList;
    private String damLakes;
    private String stations;

    @JsonGetter("created_date")
    public Object getCreatedDate() {
        try {
            return createdDate.getEpochSecond();
        } catch (Exception e) {
            return null;
        }
    }

    @JsonGetter("modified_date")
    public Object getModifiedDate() {
        try {
            return createdDate.getEpochSecond();
        } catch (Exception e) {
            return null;
        }
    }

    private Set<GroupPrivilege> groupPrivileges;
    private List<MenuListDto> menuParent;
    private List<GroupDetailDto> groupDetailDtos;
    private List<GroupPrivilege> roleList;
    private List<Long> roleId;
    private List<Long> roleAll;

    private Integer groupId;
}
