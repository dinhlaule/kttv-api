package com.neo.kttvapi.controller.api;

import com.neo.kttvapi.common.constant.ApplicationApi;
import com.neo.kttvapi.common.enums.ResponseCodeEnum;
import com.neo.kttvapi.controller.response.ResponseBodyDto;
import com.neo.kttvapi.dto.UserInfoDto;
import com.neo.kttvapi.dto.UserInfoListDto;
import com.neo.kttvapi.dto.request.UserRequest;
import com.neo.kttvapi.entity.UserInfo;
import com.neo.kttvapi.service.UserInfoService;
import com.neo.kttvapi.service.objectSearch.SearchUserManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(ApplicationApi.API_PREFIX + ApplicationApi.URI_USER_INFO)
@Slf4j
@CrossOrigin(origins = "*")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/create-if-not-exist")
    public ResponseEntity<ResponseBodyDto<UserInfoDto>> createIfNotExist(@RequestParam(name = "user_name") String user_name) {
        return ResponseEntity.ok(new ResponseBodyDto<>(userInfoService.createIfNotExistUser(user_name),ResponseCodeEnum.R_200, "OK"));
    }

    @GetMapping("/findAll")
    public ResponseEntity<ResponseBodyDto<UserInfoDto>> findAll(@ModelAttribute SearchUserManager userManager){
        log.info("Request {}",userManager);
        Page<UserInfoDto> userInfoDtos = userInfoService.findAllBy(userManager.getUser_name(), userManager.getName(), userManager.getMobile(), userManager.getEmail(), userManager.getUnit(), userManager.getGroup_privilege(),userManager.getDam_lake_and_station(), userManager.getGender(),userManager.getStatus_id(), userManager.getFrom_date(),userManager.getTo_date(), PageRequest.of(userManager.getPage(),userManager.getSize()));
        return new ResponseEntity<>(new ResponseBodyDto<>(PageRequest.of(userManager.getPage(),userManager.getSize()), userInfoDtos, ResponseCodeEnum.R_200, "OK"), HttpStatus.OK);
    }
    @GetMapping("/findById/{user_name}")
    public ResponseEntity<ResponseBodyDto<UserInfoDto>> findById(@PathVariable String user_name){
        log.info("Request {}",user_name);
        return new ResponseEntity<>(new ResponseBodyDto<>(userInfoService.findByUserName(user_name),ResponseCodeEnum.R_200,"Ok"),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ResponseBodyDto<UserInfo>> save(@RequestBody @Valid UserRequest userRequest) {
        return new ResponseEntity<>(new ResponseBodyDto<>(userInfoService.save(userRequest), ResponseCodeEnum.R_200, "OK"), HttpStatus.OK);
    }

    @GetMapping(path = "/list")
    public List<UserInfoListDto> getListUsers(){
        return userInfoService.getListUsers();
    }
}
