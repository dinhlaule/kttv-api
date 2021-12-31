package com.neo.kttvapi.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Data
@Table(name = "MENU_ACT_HISTORY")
public class MenuActionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false)
    private Long id;

    @NotEmpty
    @Max(value = 10)
    @Column(name = "MENU_ID",nullable = false)
    private Long menuId;

    @NotEmpty
    @Max(value = 100)
    @Column(name = "MENU_NAME",nullable = false)
    private String menuName;


    @Max(value = 100)
    @Column(name = "PARENT_ID")
    private Long parentId;

    @NotEmpty
    @Max(value = 100)
    @Column(name = "PICTURE_FILE",nullable = false)
    private String pictureFile;

    @NotEmpty
    @Max(value = 200)
    @Column(name = "DETAIL_FILE")
    private String detailFile;

    @NotEmpty
    @Max(value = 10)
    @Column(name = "MENU_LEVEL",nullable = false)
    private Integer menuLevel;

    @NotEmpty
    @Max(value = 10)
    @Column(name = "MENU_PRIORITY",nullable = false)
    private Integer menuPriority;

    @NotEmpty
    @Max(value = 10)
    @Column(name = "STATUS",nullable = false)
    private Integer status;


    @Column(name = "CREATED_DATE",nullable = false)
    private Date createdDate;

    @Column(name = "CREATED_USER")
    private String createdUser;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @Column(name = "UPDATED_USER")
    private String updatedUser;
}
