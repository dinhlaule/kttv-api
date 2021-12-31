package com.neo.kttvapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
    @Table(name = "group_detail")
public class GroupDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "is_group_leader")
    private Integer isGroupLeader;

    @ManyToOne
    @JoinColumn(name = "group_user_info_id")
    private GroupUserInfo groupUserInfo;

    @ManyToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;


}
