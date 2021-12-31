package com.neo.kttvapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user_info")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo extends BaseEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_name",nullable = false,unique = true,columnDefinition = "varchar2(50)")
    private String userName;

    @Column(name = "mobile",unique = true)
    private String mobile;

    @Column(name = "position")
    private String position;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "check_role")
    private Integer checkRole;

    @Column(name = "date_role")
    private String dateRole;

    @Column(name = "card_number",unique = true)
    private String cardNumber;

    @Column(name = "code")
    private String code;

    @Column(name = "office_code")
    private Integer officeCode;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "dam_lakes")
    private String damLakes;

    @Column(name = "stations")
    private String stations;

    @Column(name= "unit")
    private String unit;

    @Column(name= "date_of_birth")
    private Instant dateOfBirth;

    @ManyToMany()
    @JoinTable(name = "group_privilege_user",joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns =@JoinColumn(name = "group_privilege_id"))
    private Set<GroupPrivilege> groupPrivileges=new HashSet<>();

    @OneToMany(mappedBy = "userInfo", cascade = {CascadeType.ALL,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE},fetch = FetchType.LAZY)
    //@OneToMany
    //@JoinColumn(name = "GROUP_ID")
    private List<GroupUserInfoDetail> groupUserInfoDetail;

}
