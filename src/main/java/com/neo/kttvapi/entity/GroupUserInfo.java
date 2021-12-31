package com.neo.kttvapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "group_user_info")
public class GroupUserInfo extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "group_parent")
    private Integer groupParent;

    @Column(name = "group_level")
    private Integer groupLevel;

    @Column(name = "station_id")
    private String stationId;

    @Column(name = "status")
    private Integer status;

    @Column(name = "is_delete")
    private Integer isDelete;

    @OneToMany(mappedBy = "groupUserInfo")
    @JsonIgnore
    private Set<GroupDetail> groupDetails=new HashSet<>();
}
