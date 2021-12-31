package com.neo.kttvapi.service.impl;

import com.neo.kttvapi.dto.GroupUserSystemDto;
import com.neo.kttvapi.dto.mapper.Mapper;
import com.neo.kttvapi.entity.GroupUserInfoDetail;
import com.neo.kttvapi.entity.GroupUserSystem;
import com.neo.kttvapi.entity.UserInfo;
import com.neo.kttvapi.repository.GroupUserInfoDetailRepository;
import com.neo.kttvapi.repository.GroupUserSystemRepository;
import com.neo.kttvapi.repository.UserInfoRepository;
import com.neo.kttvapi.service.GroupUserSystemService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupUserSystemServiceImpl implements GroupUserSystemService {
    private final GroupUserSystemRepository groupUserSystemRepository;
    private final UserInfoRepository userInfoRepository;
    private final GroupUserInfoDetailRepository groupUserInfoDetailRepository;
//    private final GroupUserSystemHistoryRepository historyRepository;

    public GroupUserSystemServiceImpl(GroupUserSystemRepository groupUserSystemRepository, UserInfoRepository userInfoRepository, GroupUserInfoDetailRepository groupUserInfoDetailRepository) {
        this.groupUserSystemRepository = groupUserSystemRepository;
        this.userInfoRepository = userInfoRepository;
//        this.historyRepository = historyRepository;
        this.groupUserInfoDetailRepository = groupUserInfoDetailRepository;
    }

    @Override
    public List<GroupUserSystemDto> getAll() {
        List<GroupUserSystemDto> groupUserSystemDtoList = groupUserInfoDetailRepository.getAll();
        return groupUserSystemDtoList;
    }

    public void add(GroupUserInfoDetail ip){

        int id;
        Optional<GroupUserSystem> i = groupUserSystemRepository.findById(ip.getGroupId());
        Optional<UserInfo> p = userInfoRepository.findById(ip.getUserId());

        ip.setUserInfo(p.get());
//        p.get().getGroupUserInfoDetail().add(ip);

        ip.setGroupUserSystem(i.get());
//        i.get().getGroupUserInfoDetail().add(ip);
        groupUserInfoDetailRepository.save(ip);

    }

    @Override
    public GroupUserSystem addGroupUserSystem(GroupUserSystem groupUserSystem) {
    GroupUserSystem groupUserSystem1 = groupUserSystemRepository.save(groupUserSystem);
    groupUserSystemRepository.flush();
        return groupUserSystem1;
    }

    public void updateRelationship (GroupUserSystemDto groupUserSystemDto){
        Integer idDetail = groupUserInfoDetailRepository.findByGroupIdAndUserId(groupUserSystemDto.getId(),groupUserSystemDto.getUserIdUpdated()).get().getId();
        groupUserInfoDetailRepository.updateRelationship(idDetail, groupUserSystemDto.getId(), groupUserSystemDto.getUserId());
    }

    @Override
    public GroupUserSystemDto update(GroupUserSystemDto groupUserSystemDto, Integer id) {
        GroupUserSystem groupUserSystem = Mapper.map(groupUserSystemDto, GroupUserSystem.class);
        groupUserSystem.setId(id);
        Date date = new Date();
        groupUserSystem.setUpdatedDate(date);
        groupUserSystemRepository.save(groupUserSystem);
        groupUserSystemRepository.flush();
        updateRelationship(groupUserSystemDto);
        return groupUserSystemDto;
    }



}
//    @Override
//    public void add(GroupUserSystemDto groupUserSystemDto) {
//        GroupUserSystem groupUserSystem = Mapper.map(groupUserSystemDto, GroupUserSystem.class);
//        Date date = new Date();
//        groupUserSystem.setCreatedDate(date);
//        groupUserSystem.setUpdatedDate(date);
//        groupUserSystemRepository.saveAndFlush(groupUserSystem);
//        Optional<UserInfo> userInfos = userInfoRepository.findById(groupUserSystemDto.getUserId());
//        if(userInfos.isPresent()){
//            userInfos.get().setGroupId(groupUserSystem.getId());
//            userInfoRepository.save(userInfos.get());
//        }
//
//    }
//
//    @Override
//    public GroupUserSystemDto update(GroupUserSystemDto groupUserSystemDto, Integer id) {
//        GroupUserSystem groupUserSystem = Mapper.map(groupUserSystemDto, GroupUserSystem.class);
//        groupUserSystem.setId(id);
//        Date date = new Date();
//        groupUserSystem.setUpdatedDate(date);
//        groupUserSystemRepository.save(groupUserSystem);
//        Optional<UserInfo> userInfos = userInfoRepository.findById(groupUserSystemDto.getUserId());
//        if(userInfos.isPresent()){
//            userInfos.get().setGroupId(groupUserSystem.getId());
//            userInfoRepository.save(userInfos.get());
//        }
//        return groupUserSystemDto;
//    }

//    @Override
//    public List<GroupUserSystemDto> getHistory(Integer groupId) {
//        List<GroupUserSystemDto> groupUserSystemDtoList = historyRepository.getHistory(groupId);
//        return groupUserSystemDtoList;
//    }



