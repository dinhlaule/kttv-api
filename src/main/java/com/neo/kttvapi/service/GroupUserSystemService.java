package com.neo.kttvapi.service;

import com.neo.kttvapi.dto.GroupUserSystemDto;
import com.neo.kttvapi.dto.UserInfoListDto;
import com.neo.kttvapi.entity.GroupUserInfoDetail;
import com.neo.kttvapi.entity.GroupUserSystem;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface GroupUserSystemService {
    List<GroupUserSystemDto> getAll();
    void add(GroupUserInfoDetail ip);
    GroupUserSystem addGroupUserSystem(GroupUserSystem g);


    void updateRelationship(GroupUserSystemDto gu);
    GroupUserSystemDto update(GroupUserSystemDto groupUserSystemDto, Integer id);
//    List<GroupUserSystemDto> getHistory(Integer groupId);

}
