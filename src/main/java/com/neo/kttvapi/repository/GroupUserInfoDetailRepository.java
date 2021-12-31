package com.neo.kttvapi.repository;

import com.neo.kttvapi.dto.GroupUserSystemDto;
import com.neo.kttvapi.entity.GroupUserInfoDetail;
import com.neo.kttvapi.entity.GroupUserSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface GroupUserInfoDetailRepository extends JpaRepository<GroupUserInfoDetail, Integer> {
//    @Query("select new com.neo.kttvapi.dto.GroupUserSystemDto( " +
//            "gu.id, gu.groupUserName, u.id, u.userName, u.name, u.mobile, u.email, gu.status, gu.description, " +
//            "gu.createdDate, gu.createdBy, gu.updatedDate, gu.updatedBy) " +
//            "from GroupUserSystem gu left join UserInfo u " +
//            "on gu.id = u.groupId ")
//    List<GroupUserSystemDto> getAll();

    @Query("select distinct new com.neo.kttvapi.dto.GroupUserSystemDto(gu.groupUserSystem.id, gu.groupUserSystem.groupUserName, " +
            "gu.userInfo.id, gu.userInfo.userName, gu.userInfo.name, gu.userInfo.mobile, gu.userInfo.email, " +
            "gu.groupUserSystem.status, gu.groupUserSystem.description, gu.groupUserSystem.createdDate, gu.groupUserSystem.createdBy, gu.groupUserSystem.updatedDate, gu.groupUserSystem.updatedBy) " +
            "from GroupUserInfoDetail gu")
    List<GroupUserSystemDto> getAll();

    public Optional<GroupUserInfoDetail> findByGroupIdAndUserId(Integer groupId, Integer userId);
    @Transactional
    @Modifying
    @Query("update GroupUserInfoDetail  gu set gu.groupId= ?2, gu.userId = ?3 where gu.id = ?1")
    public void updateRelationship(Integer id, Integer groupId, Integer userId);


}
