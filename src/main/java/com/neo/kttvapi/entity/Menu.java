package com.neo.kttvapi.entity;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "MENU")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Max(value = 100)
    @Column(name = "NAME",nullable = false)
    private String name;

    @Max(value = 100)
    @Column(name = "PARENT_ID")
    private Long parentId;

    @NotEmpty
    @Max(value = 100)
    @Column(name = "PICTURE_FILE",nullable = false)
    private String pictureFile;

    @NotEmpty
    @Max(value = 200)
    @Column(name = "DETAILS_FILE")
    private String detailsFile;

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

    @NotEmpty
    @Column(name = "CREATED_DATE",nullable = false)
    private Date createdDate;

    @Column(name = "CREATED_USER")
    private String createdUser;

}
