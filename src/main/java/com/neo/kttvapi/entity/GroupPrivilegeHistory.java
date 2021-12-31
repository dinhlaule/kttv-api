package com.neo.kttvapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "GROUP_PRIVILEGE_HISTORY")
@NoArgsConstructor
@AllArgsConstructor
public class GroupPrivilegeHistory {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "GROUP_ID")
    private Integer groupId;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "MENU_ID")
    private Integer menuId;

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

    public GroupPrivilegeHistory(Integer groupId, String groupName, String description, Integer menuId, String action, Integer status, Date createdDate, String createdBy, Date updatedDate, String updatedBy) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.description = description;
        this.menuId = menuId;
        this.action = action;
        this.status = status;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
    }
}
