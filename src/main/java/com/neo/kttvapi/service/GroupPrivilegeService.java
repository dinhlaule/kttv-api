package com.neo.kttvapi.service;

import com.neo.kttvapi.dto.GroupPrivilegeDto;
import com.neo.kttvapi.entity.GroupPrivilege;
import com.neo.kttvapi.entity.GroupValueSystem;
import com.neo.kttvapi.entity.Menu;

import java.util.List;

public interface GroupPrivilegeService {
    List<GroupPrivilegeDto> getAll();
    void add(GroupPrivilegeDto groupPrivilegeDto);
    GroupPrivilegeDto update(GroupPrivilegeDto groupPrivilegeDto, Integer id);
    List<GroupPrivilegeDto> getHistory(Integer groupId);
    List<Menu> getMenus();
    List<GroupValueSystem> getActions();

    List<GroupPrivilegeDto> findAll();


}
