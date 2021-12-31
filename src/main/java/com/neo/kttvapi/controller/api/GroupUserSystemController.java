package com.neo.kttvapi.controller.api;

import com.neo.kttvapi.common.constant.ApplicationApi;
import com.neo.kttvapi.dto.GroupUserSystemDto;
import com.neo.kttvapi.entity.GroupUserInfoDetail;
import com.neo.kttvapi.entity.GroupUserSystem;
import com.neo.kttvapi.service.GroupUserSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(ApplicationApi.API_PREFIX + "/group-config")
@CrossOrigin(origins = "*")
public class GroupUserSystemController {

    private GroupUserSystemService groupUserSystemService;

    @Autowired
    public GroupUserSystemController(GroupUserSystemService groupUserSystemService) {
        this.groupUserSystemService = groupUserSystemService;
    }

    @GetMapping
    public ResponseEntity<List<GroupUserSystemDto>> getAll(){
        return ResponseEntity.ok(groupUserSystemService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<GroupUserSystemDto> add(@RequestBody @Valid GroupUserSystemDto groupUserSystemDto){
        GroupUserSystem groupUserSystem = new GroupUserSystem();
        groupUserSystem.setGroupUserName(groupUserSystemDto.getGroupUserName());
        groupUserSystem.setDescription(groupUserSystemDto.getDescription());
        groupUserSystem.setStatus(groupUserSystemDto.getStatus());
        groupUserSystem.setCreatedDate(new Date());

        groupUserSystem = groupUserSystemService.addGroupUserSystem(groupUserSystem);
        GroupUserInfoDetail groupUserInfoDetail = new GroupUserInfoDetail();
        groupUserInfoDetail.setUserId(groupUserSystemDto.getUserId());
        groupUserInfoDetail.setGroupId(groupUserSystem.getId());
        groupUserSystemService.add(groupUserInfoDetail);
        return new ResponseEntity<>(groupUserSystemDto, HttpStatus.CREATED);
    }
    @PutMapping(value = "/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GroupUserSystemDto> update(@PathVariable("id") Integer id,@RequestBody GroupUserSystemDto groupUserSystemDto){
        return new ResponseEntity<>(groupUserSystemService.update(groupUserSystemDto,id),HttpStatus.OK);
    }


//
//    @PutMapping("/{id}")
//    public ResponseEntity<GroupUserSystemDto> update(@RequestBody @Valid GroupUserSystemDto groupUserSystemDto, @PathVariable Integer id) {
//        return new ResponseEntity<>(groupUserSystemService.update(groupUserSystemDto, id), HttpStatus.OK);
//    }

//    @GetMapping("/history/{id}")
//    public ResponseEntity<List<GroupUserSystemDto>> getHistory(@PathVariable Integer id){
//
//        return ResponseEntity.ok(groupUserSystemService.getHistory(id));
//    }


}
