package com.neo.kttvapi.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.neo.kttvapi.common.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseBodyDto<E> {

    /*
     * =============================================================================
     * =================================== ===== PRIVATE PROPERTIES =====
     * =============================================================================
     * ===================================
     */

    /**
     * Reset API: Response code
     */
    private ResponseCode code;

    /**
     * Reset API: Response message
     */
    private String message;

    /**
     * Reset API: Response total items for pagination
     */

    @JsonProperty("total_items")
    private long totalItems;

    private int totalPages;

    private int numberOfElements;

    /**
     * Reset API: Response page number for pagination
     */
    private int page;

    /**
     * Reset API: Response size of one page for pagination
     */
    private int size;

    /**
     * Reset API: Response data of 1 item
     */
    private E item;

    /**
     * Reset API: Response data of list items
     */
    private List<E> items;

    public ResponseBodyDto(Pageable pageable, Page<E> page, ResponseCode code, String message) {
        this.code = code;
        this.message = message;
        this.totalItems = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.page = pageable.getPageNumber();
        this.size = pageable.getPageSize();
        this.items = page.getContent();
        this.numberOfElements=page.getNumberOfElements();
    }

    public ResponseBodyDto(E item, ResponseCode code, String message) {
        this.code = code;
        this.message = message;
        this.item = item;
    }


    public ResponseBodyDto(List<E> items, ResponseCode code, String message, int totalItems) {
        this.code = code;
        this.message = message;
        this.items = items;
        this.totalItems = totalItems;
    }

    public ResponseBodyDto(ResponseCode code, String message) {
        this.code = code;
        this.message = message;
    }

}
