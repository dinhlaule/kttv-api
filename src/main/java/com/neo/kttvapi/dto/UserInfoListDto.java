package com.neo.kttvapi.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserInfoListDto {

    private Integer id;

    private String name;

    private String mobile;

    private String email;

    private String userName;

    private String infoUser;

    public UserInfoListDto(Integer id, String name, String mobile, String email, String userName) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.userName = userName;
        this.infoUser = userName + " - " + name + " - " + mobile + " - " + email ;
    }
}
