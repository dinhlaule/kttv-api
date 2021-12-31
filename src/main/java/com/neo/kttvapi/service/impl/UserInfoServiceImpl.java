package com.neo.kttvapi.service.impl;

import com.neo.kttvapi.common.error.BadRequestException;
import com.neo.kttvapi.dto.MenuListDto;
import com.neo.kttvapi.dto.UserInfoDto;
import com.neo.kttvapi.dto.UserInfoListDto;
import com.neo.kttvapi.dto.mapper.Mapper;
import com.neo.kttvapi.dto.request.UserRequest;
import com.neo.kttvapi.entity.*;
import com.neo.kttvapi.repository.*;
import com.neo.kttvapi.service.UserInfoService;
import com.neo.kttvapi.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private LogsUserInfoRepository logsUserInfoRepository;

    @Autowired
    private GroupPrivilegeRepository groupPrivilegeRepository;

    @Autowired
    private LogActionDetailRepository logActionDetailRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Override
    @Transactional
    public UserInfoDto createIfNotExistUser(String user_name) {
        UserInfoDto userInfoDto=new UserInfoDto();
        if (!userInfoRepository.existsUserInfoByUserName(user_name)) {
            UserInfo userInfo=new UserInfo();
            userInfo.setUserName(user_name);
            userInfo.setCreatedBy(user_name);
            userInfo.setCreatedDate(Instant.now());
            userInfo.setModifiedDate(Instant.now());
            userInfo.setStatusId(1);
            UserInfo user= userInfoRepository.save(userInfo);
            if(user!=null){
                LogsActionDetail logsActionDetail = Util.setLogsActionDetail("","ADD",user.getUserName(),"USER_INFO","Thêm mới user");
                logActionDetailRepository.save(logsActionDetail);
                log.info("Thêm mới user với user_name: {}",user.getUserName());
            }
        }else {
            UserInfo user= userInfoRepository.getUserInfoByUserNameAndStatusId(user_name,1);
            if(user==null){
                throw new BadRequestException("Người dùng "+user_name+" đang bị khóa");
            }
            Mapper.copyPropertiesIgnoreNull(user,userInfoDto);
            List<MenuListDto> menus=new ArrayList<>();
            for (GroupPrivilege groupPrivilege:user.getGroupPrivileges()) {
                if(groupPrivilege.getStatus()==1){
                    MenuListDto menuListDto=new MenuListDto();
                    Menu menu= menuRepository.getMenuByIdAndStatusAndMenuLevel(Long.valueOf(groupPrivilege.getMenuId()),1,1);
                    if(menu!=null){
                        List<Menu> listChildren=menuRepository.getMenusByParentIdAndStatusAndMenuLevel(menu.getId(),1,2);
                        Mapper.copyPropertiesIgnoreNull(menu,menuListDto);
                        if(listChildren.size()>0){
                            Collections.sort(listChildren, (a, b) -> a.getMenuPriority().compareTo(b.getMenuPriority()));
                            menuListDto.setMenuChildren(listChildren);
                        }
                        menus.add(menuListDto);
                    }
                }
            }
            Collections.sort(menus, (a, b) -> a.getMenuPriority().compareTo(b.getMenuPriority()));
            userInfoDto.setMenuParent(menus);
            log.info("Thông tin user: {}",userInfoDto);
        }
        return userInfoDto;
    }

    @Override
    public Page<UserInfoDto> findAllBy(String userName, String name, String mobile, String email,String unit,String groupPrivilege, String damLakeAndStation,String gender, String statusId, Long fromDate, Long toDate, Pageable pageable) {
//        Page<UserInfo> userInfos = userInfoRepository.findAll(userName.isEmpty()?null:userName, name.isEmpty()?null:name, mobile.isEmpty()?null:mobile, email.isEmpty()?null:email.toLowerCase(),unit.isEmpty()?null:unit.toLowerCase(),groupPrivilege.isEmpty()?null:groupPrivilege,damLakeAndStation.isEmpty()?null:damLakeAndStation,gender.isEmpty()?null:gender, statusId.isEmpty()?null:statusId,  fromDate == null ? Instant.ofEpochSecond(0) : Instant.ofEpochSecond(fromDate), toDate == null ? Instant.now() : Instant.ofEpochSecond(toDate), pageable);
        Page<UserInfo> userInfos = userInfoRepository.findAll(userName, name, mobile, email,unit,groupPrivilege,damLakeAndStation,damLakeAndStation,gender==null?null:Integer.parseInt(gender), statusId==null?null:Integer.parseInt(statusId),  fromDate == null ? Instant.ofEpochSecond(0) : Instant.ofEpochSecond(fromDate), toDate == null ? Instant.now() : Instant.ofEpochSecond(toDate), pageable);
        Page<UserInfoDto> userInfoDtos = Mapper.toPage(userInfos, UserInfoDto.class, pageable);
        for (UserInfoDto userInfoDto : userInfoDtos) {
            StringBuilder groupRoles = new StringBuilder();
            StringBuilder damLakesAndStation = new StringBuilder();
            for (GroupPrivilege privilege : userInfoDto.getGroupPrivileges()) {
                groupRoles.append(privilege.getGroupName()).append(", ");
            }
            if (groupRoles.length() > 2) {
                groupRoles.delete(groupRoles.length() - 2, groupRoles.length());
            }
            if(userInfoDto.getDamLakes()!=null){
                damLakesAndStation.append(userInfoDto.getDamLakes()).append(",");
            }
            if(userInfoDto.getStations()!=null){
                damLakesAndStation.append(userInfoDto.getStations()).append(",");
            }
            if(damLakesAndStation.length()>=2){
                damLakesAndStation.delete(damLakesAndStation.length()-1,damLakesAndStation.length());
            }
            userInfoDto.setDamLakeAndStation(damLakesAndStation.toString());
            userInfoDto.setRoleGroups(String.valueOf(groupRoles));
        }
        return userInfoDtos;
    }

    @Override
    public UserInfoDto findByUserName(String userName) {
        UserInfo userInfo= userInfoRepository.getUserInfoByUserName(userName);
        List<GroupPrivilege> groupPrivileges=new ArrayList<>(userInfo.getGroupPrivileges());
        List<Long> roleIdCurrent=new ArrayList<>();
        List<Long> roleAll=new ArrayList<>();
        for (GroupPrivilege groupPrivilege: groupPrivileges) {
            roleIdCurrent.add(Long.valueOf(groupPrivilege.getId()));
        }
        List<GroupPrivilege> groupPrivilegeList=groupPrivilegeRepository.findAll();
        for (GroupPrivilege role: groupPrivilegeList){
            roleAll.add(Long.valueOf(role.getId()));
        }
        UserInfoDto userInfoDto=Mapper.map(userInfo,UserInfoDto.class);
        userInfoDto.setRoleAll(roleAll);
        userInfoDto.setRoleId(roleIdCurrent);
        userInfoDto.setRoleList(groupPrivileges);
        if(userInfo.getStations()!=null){
            userInfoDto.setStationsList(userInfo.getStations().split(", "));
        }
        if(userInfo.getDamLakes()!=null){
            userInfoDto.setDamLakesList(userInfo.getDamLakes().split(", "));
        }
        return userInfoDto;
    }


    @Override
    @Transactional
    public UserInfo save(UserRequest userRequest) {
        log.info("Cập nhật user_info: [{}}",userRequest);
        UserInfo userInfo=null;
        if(userRequest.getId()!=0){
            userInfo=userInfoRepository.getUserInfoById(userRequest.getId());
        }
        if(userInfo==null){
            log.error("Lỗi cập nhật, Không tìm thấy user_id: {}",userRequest.getId());
            throw new BadRequestException("Không tìm thấy user_id");
        }
        if(userInfo.getCardNumber()==null){
            userInfo.setCardNumber("");
        }
        if(!userInfo.getCardNumber().equals(userRequest.getCardNumber())){
            if(userInfoRepository.countUserInfoByCardNumber(userRequest.getCardNumber())>0){
                log.error("Số chứng minh thư {} đã tồn tại",userRequest.getCardNumber());
                throw new BadRequestException("Số chứng minh thư đã tồn tại !");
            }
        }
        if(userInfo.getEmail()==null){
            userInfo.setEmail("");
        }
        if(!userInfo.getEmail().equals(userRequest.getEmail())){
            if(userInfoRepository.countUserInfoByEmail(userRequest.getEmail())>0){
                log.error("Email {} đã tồn tại",userRequest.getEmail());
                throw new BadRequestException("Email đã tồn tại !");
            }
        }
        if(userInfo.getMobile()==null){
            userInfo.setMobile("");
        }
        if(!userInfo.getMobile().equals(userRequest.getMobile())){
            if(userInfoRepository.countUserInfoByMobile(userRequest.getMobile())>0){
                log.error("Số điện thoại {} đã tồn tại",userRequest.getMobile());
                throw new BadRequestException("Số điện thoại đã tồn tại !");
            }
        }
        Mapper.copyPropertiesIgnoreNull(userRequest, userInfo);
        Set<GroupPrivilege> groupPrivileges = new HashSet<>();
        for (Integer value : userRequest.getRoleList()) {
            groupPrivileges.add(groupPrivilegeRepository.getGroupPrivilegeById(value));
        }
        userInfo.setGroupPrivileges(groupPrivileges);
        userInfo.setModifiedBy(userRequest.getUser_auth());
        userInfo.setModifiedDate(Instant.now());
        String station=Arrays.toString(userRequest.getStations());
        String damLake=Arrays.toString(userRequest.getDamLakes());
        userInfo.setStations(station.substring(1,station.length()-1));
        userInfo.setDamLakes(damLake.substring(1,damLake.length()-1));
        UserInfo info = userInfoRepository.save(userInfo);
        if(info!=null){
            log.info("Cập nhật thành công user_name: {}",info.getUserName());
            LogsActionDetail logsActionDetail = Util.setLogsActionDetail("","EDIT",userRequest.getUser_auth(),"USER_INFO","Cập nhật user");
            logActionDetailRepository.save(logsActionDetail);
            LogsUserInfo logsUserInfo=new LogsUserInfo();
            Mapper.copyPropertiesIgnoreNull(info,logsUserInfo);
            StringBuilder groupPrivilegeList=new StringBuilder();
            for (GroupPrivilege groupPrivilege:info.getGroupPrivileges()){
                groupPrivilegeList.append(groupPrivilege.getGroupName()).append(", ");
            }
            if(groupPrivilegeList.length()>2){
                groupPrivilegeList.delete(groupPrivilegeList.length()-2,groupPrivilegeList.length());
            }
            logsUserInfo.setGroupPrivilegeList(groupPrivilegeList.toString());
            logsUserInfo.setModifiedBy(info.getModifiedBy());
            logsUserInfo.setModifiedDate(Instant.now());
            logsUserInfo.setId(0);
            logsUserInfoRepository.save(logsUserInfo);
            log.info("Cập nhật bảng logsUserInfo với user_name: {}",logsUserInfo.getUserName());
        }
        else{
            log.error("Cập nhật thất bại user_info_id: {}",info.getUserName());
            throw new BadRequestException("Lỗi cập nhật");
        }
        return info;
    }
    @Override
    public List<UserInfoListDto> getListUsers(){
        return userInfoRepository.getListUsers();
    }


}
