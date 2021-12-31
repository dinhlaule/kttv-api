package com.neo.kttvapi.service.objectSearch;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.Instant;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SearchUserManager implements Serializable {

    @JsonProperty("user_info_id")
    private long user_info_id;

    @JsonProperty("page")
    private int page;

    @JsonProperty("size")
    private int size;

    @JsonProperty("user_name")
    private String user_name;

    @JsonProperty("name")
    private String name;

    @JsonProperty("mobile")
    private String mobile;

    @JsonProperty("email")
    private String email;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("card_number")
    private String card_number;

    @JsonProperty("group_privilege")
    private String group_privilege;

    @JsonProperty("status_id")
    private String status_id;

    @JsonProperty("from_date")
    private Long from_date;

    @JsonProperty("to_date")
    private Long to_date;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("dam_lake_and_station")
    private String dam_lake_and_station;

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setUser_name(String user_name) {
        this.user_name = StringUtils.isBlank(user_name)?null:user_name.toLowerCase();
    }

    public void setName(String name) {
        this.name = StringUtils.isBlank(name)?null:name.toLowerCase();
    }

    public void setMobile(String mobile) {
        this.mobile = StringUtils.isBlank(mobile)?null:mobile.toLowerCase();
    }

    public void setEmail(String email) {
        this.email = StringUtils.isBlank(email)?null:email.toLowerCase();
    }

    public void setGender(String gender) {
        this.gender = StringUtils.isBlank(gender)?null:gender;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public void setGroup_privilege(String group_privilege) {
        this.group_privilege = StringUtils.isBlank(group_privilege)?null:group_privilege.toLowerCase();
    }

    public void setStatus_id(String status_id) {
        this.status_id = StringUtils.isBlank(status_id)?null:status_id;
    }

    public void setFrom_date(Long from_date) {
        this.from_date = from_date;
    }

    public void setTo_date(Long to_date) {
        this.to_date = to_date;
    }

    public void setUnit(String unit) {
        this.unit = StringUtils.isBlank(unit)?null:unit.toLowerCase();
    }

    public void setDam_lake_and_station(String dam_lake_and_station) {
        this.dam_lake_and_station = StringUtils.isBlank(dam_lake_and_station)?null:dam_lake_and_station.toLowerCase();
    }

}
