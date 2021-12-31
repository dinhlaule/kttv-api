package com.neo.kttvapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GROUP_USER_INFO_DETAIL")
public class GroupUserInfoDetail {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "GROUP_ID", nullable = false,insertable = false, updatable = false)
    private Integer groupId;

    @Column(name = "USER_ID", nullable = false,insertable = false, updatable = false)
    private Integer userId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "GROUP_ID",referencedColumnName = "ID")
    private GroupUserSystem groupUserSystem;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private UserInfo userInfo;
}
