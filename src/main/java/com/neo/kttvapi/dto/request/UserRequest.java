package com.neo.kttvapi.dto.request;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {
    @NotEmpty
    private long id;

    @NotEmpty(message = "name không được để trống")
    @Size(max = 200, message = "name dài tối đa 100 ký tự")
    private String name;

    @NotEmpty(message = "userName không được để trống")
    @JsonProperty("user_name")
    private String userName;

    @Pattern(regexp = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$",message = "Số điện thoại không đúng định dạng")
    private String mobile;

    private String position;

    @NotEmpty(message = "email không được để trống")
    @Size(max = 100, message = "email dài tối đa 100 ký tự")
    @Email(message = "Email không đúng định dạng")
    private String email;

    private Integer gender;

    @NotEmpty(message = "status không được để trống")
    @JsonProperty("status_id")
    private Integer statusId;

    @NotEmpty(message = "dateRole không được để trống")
    @JsonProperty("date_role")
    private String dateRole;

    @JsonProperty("card_number")
    private String cardNumber;

    @Size(max = 1000, message = "unit dài tối đa 1000 ký tự")
    private String unit;

    @Size(max = 4000, message = "dam_lakes dài tối đa 4000 ký tự")
    @JsonProperty("dam_lakes")
    private String[] damLakes;

    @Size(max = 4000, message = "stations dài tối đa 4000 ký tự")
    @JsonProperty("stations")
    private String[] stations;

    @NotEmpty(message = "user_auth không được để trống")
    @JsonProperty("user_auth")
    private String user_auth;

    @JsonProperty("role_list")
    private List<Integer> roleList;

}
