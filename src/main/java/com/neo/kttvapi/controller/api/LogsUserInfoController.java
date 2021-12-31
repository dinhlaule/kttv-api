package com.neo.kttvapi.controller.api;

import com.neo.kttvapi.common.constant.ApplicationApi;
import com.neo.kttvapi.common.enums.ResponseCodeEnum;
import com.neo.kttvapi.controller.response.ResponseBodyDto;
import com.neo.kttvapi.dto.LogsUserInfoDto;
import com.neo.kttvapi.service.LogsUserInfoService;
import com.neo.kttvapi.service.objectSearch.SearchUserManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApplicationApi.API_PREFIX + ApplicationApi.URI_LOG_USER_INFO)
@Slf4j
public class LogsUserInfoController {

    @Autowired
    private LogsUserInfoService logsUserInfoService;

    @GetMapping("/findAll")
    public ResponseEntity<ResponseBodyDto<LogsUserInfoDto>> findAll(@ModelAttribute SearchUserManager userManager){
        log.info("Request {}",userManager);
        Page<LogsUserInfoDto> logsUserInfoDtos = logsUserInfoService.findAllBy(userManager.getUser_name(),userManager.getName(), userManager.getMobile(), userManager.getEmail(), userManager.getUnit(), userManager.getGroup_privilege(),userManager.getDam_lake_and_station(), userManager.getGender(),userManager.getStatus_id(), userManager.getFrom_date(),userManager.getTo_date(), PageRequest.of(userManager.getPage(),userManager.getSize()));
        return new ResponseEntity<>(new ResponseBodyDto<>(PageRequest.of(userManager.getPage(),userManager.getSize()), logsUserInfoDtos, ResponseCodeEnum.R_200, "OK"), HttpStatus.OK);
    }
}
