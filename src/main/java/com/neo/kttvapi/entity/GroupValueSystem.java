package com.neo.kttvapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "GROUP_VALUES_SYSTEM")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupValueSystem {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @Column(name = "PARA_NAME")
    private String paraName;

    @Column(name = "PARA_VALUE")
    private Integer paraValue;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "NOTE")
    private String note;
}
