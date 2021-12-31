package com.neo.kttvapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "GROUP_PRIVILEGE")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupPrivilege {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Tên nhóm không được để trống")
    @Max(value = 50, message = "Tên nhóm không quá 50 kí tự")
    @Column(name = "GROUP_NAME")
    private String groupName;

    @Max(value = 200, message = "Mô tả không quá 200 kí tự")
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull(message = "Menu tác động không được để trống")
    @Column(name = "MENU_ID")
    private Integer menuId;

    @NotEmpty(message = "Action thực hiện không được để trống")
    @Max(value = 50, message = "Action không quá 50 kí tự")
    @Column(name = "ACTION")
    private String action;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @ManyToMany(mappedBy = "groupPrivileges")
    @JsonIgnore
    private Set<UserInfo> userInfoSet=new HashSet<>();
}
