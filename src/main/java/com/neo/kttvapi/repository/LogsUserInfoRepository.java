package com.neo.kttvapi.repository;

import com.neo.kttvapi.entity.LogsUserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface LogsUserInfoRepository extends JpaRepository<LogsUserInfo,Long> {

    LogsUserInfo findTopByOrderByModifiedDateDesc();

    @Query("SELECT l FROM LogsUserInfo l "+
            "WHERE (:name is null or lower(l.name) like %:name%) "+
            "AND (:mobile is null or lower(l.mobile) like %:mobile%) "+
            "AND (lower(l.userName) like %:userName%) "+
            "AND (:email is null or lower(l.email) like %:email%) "+
            "AND (:unit is null or lower(l.unit) like %:unit%) "+
            "AND (:groupPrivilege is null or lower(l.groupPrivilegeList) like %:groupPrivilege%) "+
            "AND (:damLake is null or lower(l.damLakes) like %:damLake%) "+
            "AND (:station is null or lower(l.stations) like %:station%) "+
            "AND (:statusId is null or l.statusId =:statusId )"+
            "AND (:fromDate is null or l.modifiedDate >= :fromDate) " +
            "AND (:toDate is null or l.modifiedDate <= :toDate) " +
            "AND (:gender is null or l.gender = :gender) "+
            "ORDER BY l.modifiedDate DESC "
    )
    Page<LogsUserInfo> findAll(@Param("userName") String userName,@Param("name") String name,
                               @Param("mobile") String mobile,
                               @Param("email") String email,
                               @Param("unit") String unit,
                               @Param("groupPrivilege") String groupPrivilege,
                               @Param("damLake") String damLake,
                               @Param("station") String station,
                               @Param("gender") Integer gender,
                               @Param("statusId") Integer statusId,
                               @Param("fromDate") Instant fromDate,
                               @Param("toDate") Instant toDate,
                               Pageable pageable);

}
