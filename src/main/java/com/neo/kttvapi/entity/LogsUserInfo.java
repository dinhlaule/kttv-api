package com.neo.kttvapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="logs_user_info")
public class LogsUserInfo extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_info_id")
    private long userInfoId;

    @Column(name = "name")
    private String name;

    @Column(name = "user_name",nullable = false,columnDefinition = "varchar2(50)")
    private String userName;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "position")
    private String position;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "check_role")
    private Integer checkRole;

    @Column(name = "date_role")
    private String dateRole;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "code")
    private String code;

    @Column(name = "office_code")
    private Integer officeCode;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "group_privileges")
    private String groupPrivilegeList;

    @Column(name = "unit")
    private String unit;

    @Column(name = "dam_lakes")
    private String damLakes;

    @Column(name = "stations")
    private String stations;
}
