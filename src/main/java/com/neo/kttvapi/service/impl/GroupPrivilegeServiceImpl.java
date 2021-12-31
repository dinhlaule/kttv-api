package com.neo.kttvapi.service.impl;

import com.neo.kttvapi.common.error.NotFoundException;
import com.neo.kttvapi.dto.GroupPrivilegeDto;
import com.neo.kttvapi.dto.mapper.Mapper;
import com.neo.kttvapi.entity.*;
import com.neo.kttvapi.repository.GroupPrivilegeHistoryRepository;
import com.neo.kttvapi.repository.GroupPrivilegeRepository;
import com.neo.kttvapi.repository.GroupValueSystemRepository;
import com.neo.kttvapi.service.GroupPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupPrivilegeServiceImpl implements GroupPrivilegeService {
    private final GroupPrivilegeRepository groupPrivilegeRepository;
    private final GroupValueSystemRepository groupValueSystemRepository;
    private final GroupPrivilegeHistoryRepository historyRepository;
//    String[] actionId = {"0","1","2","3"};
//    String[] actionName = {"Xem","Thêm","Sửa","Xóa"};
    Map<Integer,String> action = new HashMap<>();

    @Autowired
    public GroupPrivilegeServiceImpl(GroupPrivilegeRepository groupPrivilegeRepository, GroupValueSystemRepository groupValueSystemRepository, GroupPrivilegeHistoryRepository historyRepository) {
        this.groupPrivilegeRepository = groupPrivilegeRepository;
        this.groupValueSystemRepository = groupValueSystemRepository;
        this.historyRepository = historyRepository;
        List<GroupValueSystem> groupValueSystems = groupValueSystemRepository.findByGroupName("ACTION");
        for(GroupValueSystem g : groupValueSystems){
            action.put(g.getParaValue(), g.getParaName());
        }
    }


    @Override
    public List<GroupPrivilegeDto> getAll() {
        List<GroupPrivilegeDto> groupPrivilegeDtoList = groupPrivilegeRepository.getAll();
        return handleGroupPrivilegeDtos(groupPrivilegeDtoList);
    }

    @Override
    public void add(GroupPrivilegeDto groupPrivilegeDto) {
        GroupPrivilege groupPrivilege = Mapper.map(groupPrivilegeDto, GroupPrivilege.class);
        Date date = new Date();
        groupPrivilege.setCreatedDate(date);
        //groupPrivilege.setUpdatedDate(date);
        groupPrivilegeRepository.save(groupPrivilege);
    }

    @Override
    public GroupPrivilegeDto update(GroupPrivilegeDto groupPrivilegeDto, Integer id) {
        Optional<GroupPrivilege> existingGroupPrivilege = groupPrivilegeRepository.findById(id);
        if (!existingGroupPrivilege.isPresent()) {
            throw new NotFoundException("Không tồn tại nhóm quyền");
        }
        GroupPrivilege groupPrivilege = Mapper.map(groupPrivilegeDto, GroupPrivilege.class);
        groupPrivilege.setId(id);
        Date date = new Date();
        groupPrivilege.setUpdatedDate(date);
        //groupPrivilege.setUpdatedBy("administrator");
        groupPrivilegeRepository.save(groupPrivilege);
        GroupPrivilegeHistory history = Mapper.map(groupPrivilegeDto, GroupPrivilegeHistory.class);
        history.setGroupId(id);
        history.setUpdatedDate(date);
//        GroupPrivilegeHistory history = new GroupPrivilegeHistory(
//               id,
//               groupPrivilege.getGroupName(),
//               groupPrivilege.getDescription(),
//               groupPrivilege.getMenuId(),
//               groupPrivilege.getAction(),
//               groupPrivilege.getStatus(),
//               groupPrivilege.getCreatedDate(),
//               groupPrivilege.getCreatedBy(),
//               groupPrivilege.getUpdatedDate(),
//               groupPrivilege.getUpdatedBy()
//        );
        historyRepository.save(history);
        return groupPrivilegeDto;
    }

    @Override
    public List<GroupPrivilegeDto> getHistory(Integer groupId) {
        List<GroupPrivilegeDto> groupPrivilegeDtoList = historyRepository.getHistory(groupId);
        return handleGroupPrivilegeDtos(groupPrivilegeDtoList);
    }

    private List<GroupPrivilegeDto> handleGroupPrivilegeDtos(List<GroupPrivilegeDto> groupPrivilegeDtoList) {
        for(GroupPrivilegeDto groupPrivilege : groupPrivilegeDtoList){
            // Handle action
            if(groupPrivilege.getAction().contains(",")){
                String[] temp = groupPrivilege.getAction().split(",");
                Integer[] actionIdList = new Integer[temp.length];
                for(int i = 0;i < temp.length;i++) {
                    try {
                        actionIdList[i] = Integer.parseInt(temp[i]);
                    }
                    catch (NumberFormatException nfe) {
                        //Do nothing or you could print error if you want
                    }
                }
                groupPrivilege.setActionArray(actionIdList);
                List<String> actionNameList = new ArrayList<>();
                for(int i = 0; i < actionIdList.length; i++){
                    actionNameList.add(action.get(actionIdList[i]));
                }
                groupPrivilege.setActionName(String.join(", ", actionNameList));
            }
            else {
                groupPrivilege.setActionArray(new Integer[]{ Integer.valueOf(groupPrivilege.getAction()) });
                groupPrivilege.setActionName(action.get(Integer.valueOf(groupPrivilege.getAction())));
            }

            //Handle status
            if(groupPrivilege.getStatus() == 1) groupPrivilege.setStatusName("Đang hoạt động");
            else if(groupPrivilege.getStatus() == 0) groupPrivilege.setStatusName("Không hoạt động");

        }
        return groupPrivilegeDtoList;
    }

    @Override
    public List<Menu> getMenus(){
        return groupPrivilegeRepository.getMenus();
    }

    @Override
    public List<GroupValueSystem> getActions() {
        return groupValueSystemRepository.findByGroupName("ACTION");
    }

    @Override
    public List<GroupPrivilegeDto> findAll() {
        return Mapper.toList(groupPrivilegeRepository.findAll(),GroupPrivilegeDto.class);
    }
}
