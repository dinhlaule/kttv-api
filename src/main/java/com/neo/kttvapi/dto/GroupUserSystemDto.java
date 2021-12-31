package com.neo.kttvapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

//@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GroupUserSystemDto {

    private Integer id;

    private String groupUserName;

    private Integer userId;

    private Integer userIdUpdated;

    private String userName;

    private String name;

    private String mobile;

    private String email;

    private Integer status;

    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date createdDate;

    private String createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date updatedDate;

    private String updatedBy;

    public GroupUserSystemDto(Integer id, String groupUserName, Integer userId, String userName, String name, String mobile, String email, Integer status, String description, Date createdDate, String createdBy, Date updatedDate, String updatedBy) {
        this.id = id;
        this.groupUserName = groupUserName;
        this.userId = userId;
        this.userIdUpdated = userId;
        this.userName = userName;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.status = status;
        this.description = description;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
    }
}
