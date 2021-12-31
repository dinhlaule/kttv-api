package com.neo.kttvapi.service;


import com.neo.kttvapi.dto.UserInfoDto;
import com.neo.kttvapi.dto.UserInfoListDto;
import com.neo.kttvapi.dto.request.UserRequest;
import com.neo.kttvapi.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserInfoService {
    List<UserInfoListDto> getListUsers();
    UserInfoDto createIfNotExistUser(String user_name);

    Page<UserInfoDto> findAllBy(String userName, String name, String mobile, String email,String unit, String groupPrivilege,String damLakeAndStation,String gender, String status,Long fromDate, Long toDate, Pageable pageable);

    UserInfoDto findByUserName(String userName);

    UserInfo save(UserRequest userRequest);
}
