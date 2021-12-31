package com.neo.kttvapi.service;

import com.neo.kttvapi.dto.LogsUserInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LogsUserInfoService {

    Page<LogsUserInfoDto> findAllBy(String userName,String name, String mobile, String email, String unit, String groupPrivilege, String damLakeAndStation, String gender, String status, Long fromDate, Long toDate, Pageable pageable);

}
