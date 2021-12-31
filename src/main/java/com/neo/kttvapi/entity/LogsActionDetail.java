package com.neo.kttvapi.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "logs_action_detail")
public class LogsActionDetail {
    @Id
    @Column(name = "ins_param_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "menu_name")
    private String menuName;

    @Column
    private String action;

    @Column(name = "user_act")
    private String userAct;

    @Column(name = "date_act")
    private Instant dateAct;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "content")
    private String content;
}
