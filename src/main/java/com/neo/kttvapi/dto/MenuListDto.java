package com.neo.kttvapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.neo.kttvapi.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuListDto {
    public MenuListDto(Long menuid, String name,Long parentId, String parentname,String pictureFile ,String detailsFile, Integer menuLevel,Integer menuPriority,String createdUser, Date createdDate, Integer statusInt) {
        this.menuid = menuid;
        this.name = name;
        this.parentname = parentname;
        this.parentId = parentId;
        this.pictureFile = pictureFile;
        this.detailsFile = detailsFile;
        this.menuLevel = menuLevel;
        this.menuPriority = menuPriority;
        this.createdUser = createdUser;
        this.createdDate = createdDate;
        this.statusInt = statusInt;
        if(this.statusInt == 0){
            this.status = "Vô hiệu hóa";
        } else {
            this.status = "Còn hiệu lực";
        }
    }
    @NotEmpty
    private Long menuid;

    @NotEmpty
    private String name;

    private Long parentId;

    private String parentname;

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

    private Integer statusInt;

    private String status;

    private List<Menu> menuChildren;
}
