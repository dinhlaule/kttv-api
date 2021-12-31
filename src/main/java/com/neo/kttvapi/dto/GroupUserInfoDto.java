package com.neo.kttvapi.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GroupUserInfoDto {

    private long id;
    private String name;
    private String description;
    private Integer groupParent;
    private Integer groupLevel;
    private String stationId;
    private Integer status;
    private Integer isDelete;
    private Instant createdDate;
    private Instant modifiedDate;
    private String createdBy;
    private String modifiedBy;

    @JsonGetter("created_date")
    public Object getCreatedDate() {
        try {
            return createdDate.getEpochSecond();
        } catch (Exception e) {
            return null;
        }
    }

    @JsonGetter("modified_date")
    public Object getModifiedDate() {
        try {
            return createdDate.getEpochSecond();
        } catch (Exception e) {
            return null;
        }
    }
}
