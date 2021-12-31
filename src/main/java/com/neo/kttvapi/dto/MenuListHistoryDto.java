package com.neo.kttvapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class MenuListHistoryDto {
    public MenuListHistoryDto(Long menuid, String name, Long parentId, String parentName, String pictureFile , String detailsFile, Integer menuLevel, Integer menuPriority,String createdUser, Date createdDate, Integer status, String updatedUser,Date updatedDate) {
        this.menuid = menuid;
        this.name = name;
        this.parentId = parentId;
        this.parentName = parentName;
        this.pictureFile = pictureFile;
        this.detailsFile = detailsFile;
        this.menuLevel = menuLevel;
        this.menuPriority = menuPriority;
        this.createdUser = createdUser;
        this.createdDate = createdDate;
        this.status = status;
        this.updatedDate = updatedDate;
        this.updatedUser = updatedUser;
    }

    @NotEmpty
    private Long menuid;

    @NotEmpty
    private String name;

    private Long parentId;

    private String parentName;

    @NotEmpty
    private String pictureFile;

    @NotEmpty
    private String detailsFile;

    @NotEmpty
    private Integer menuLevel;

    @NotEmpty
    private Integer menuPriority;

    private String createdUser;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date createdDate;

    @NotEmpty
    private Integer status;
    private String updatedUser;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date updatedDate;
}
