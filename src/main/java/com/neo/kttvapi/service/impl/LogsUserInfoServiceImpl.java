package com.neo.kttvapi.service.impl;

import com.neo.kttvapi.dto.LogsUserInfoDto;
import com.neo.kttvapi.dto.UserInfoDto;
import com.neo.kttvapi.dto.mapper.Mapper;
import com.neo.kttvapi.entity.GroupPrivilege;
import com.neo.kttvapi.entity.LogsUserInfo;
import com.neo.kttvapi.entity.UserInfo;
import com.neo.kttvapi.repository.LogsUserInfoRepository;
import com.neo.kttvapi.service.LogsUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class LogsUserInfoServiceImpl implements LogsUserInfoService {

    @Autowired
    private LogsUserInfoRepository logsUserInfoRepository;

    @Override
    public Page<LogsUserInfoDto> findAllBy(String userName,String name, String mobile, String email, String unit, String groupPrivilege, String damLakeAndStation, String gender, String statusId, Long fromDate, Long toDate, Pageable pageable) {
        Page<LogsUserInfo> logsUserInfos = logsUserInfoRepository.findAll(userName,name, mobile, email,unit,groupPrivilege,damLakeAndStation,damLakeAndStation,gender==null?null:Integer.parseInt(gender), statusId==null?null:Integer.parseInt(statusId),  fromDate == null ? Instant.ofEpochSecond(0) : Instant.ofEpochSecond(fromDate), toDate == null ? Instant.now() : Instant.ofEpochSecond(toDate), pageable);
        Page<LogsUserInfoDto> logsUserInfoDtos = Mapper.toPage(logsUserInfos, LogsUserInfoDto.class, pageable);
        for (LogsUserInfoDto logsUserInfoDto: logsUserInfoDtos
        ) {
            StringBuilder stringBuilder=new StringBuilder();
            if(logsUserInfoDto.getDamLakes()!=null){
                stringBuilder.append(logsUserInfoDto.getDamLakes()).append(",");
            }
            if(logsUserInfoDto.getStations()!=null){
                stringBuilder.append(logsUserInfoDto.getStations()).append(",");
            }
            if(stringBuilder.length()>=2){
                stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length());
            }
            logsUserInfoDto.setDamLakeAndStation(stringBuilder.toString());
        }
        return logsUserInfoDtos;
    }
}
