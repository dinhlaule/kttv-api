package com.neo.kttvapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GroupPrivilegeDto {
    private Integer id;

    @NotEmpty(message = "Tên nhóm không được để trống")
    @Max(value = 50, message = "Tên nhóm không quá 50 kí tự")
    private String groupName;

    @Max(value = 200, message = "Mô tả không quá 200 kí tự")
    private String description;

    @NotNull(message = "Menu tác động không được để trống")
    private Integer menuId;

    private String menuName;

    @NotEmpty(message = "Action thực hiện không được để trống")
    @Max(value = 50, message = "Action không quá 50 kí tự")
    private String action;

    private String actionName;

    private Integer[] actionArray;

    private Integer status;

    private String statusName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date createdDate;

    private String createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date updatedDate;

    private String updatedBy;

    public GroupPrivilegeDto(Integer id, String groupName, String description, Integer menuId, String menuName, String action, Integer status, Date createdDate, String createdBy, Date updatedDate, String updatedBy) {
        this.id = id;
        this.groupName = groupName;
        this.description = description;
        this.menuId = menuId;
        this.menuName = menuName;
        this.action = action;
        this.status = status;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
    }
}
