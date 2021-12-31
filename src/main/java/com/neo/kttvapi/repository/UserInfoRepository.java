package com.neo.kttvapi.repository;

import com.neo.kttvapi.dto.UserInfoListDto;
import com.neo.kttvapi.entity.GroupUserSystem;
import com.neo.kttvapi.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    UserInfo getUserInfoByUserName(String userName);

    UserInfo getUserInfoById(Long id);

    boolean existsUserInfoByUserName(String user_name);

    UserInfo getUserInfoByUserNameAndStatusId(String user_name, int status_id);

    @Query(value = "select count(u.card_number) from user_info u where u.card_number=:cardNumber",nativeQuery = true)
    int countUserInfoByCardNumber(@Param("cardNumber") String cardNumber);

    @Query(value = "select count(u.email) from user_info u where u.email=:email",nativeQuery = true)
    int countUserInfoByEmail(@Param("email") String email);

    @Query(value = "select count(u.mobile) from user_info u where u.mobile=:mobile",nativeQuery = true)
    int countUserInfoByMobile(@Param("mobile") String mobile);

    @Query("SELECT distinct u FROM UserInfo u LEFT JOIN u.groupPrivileges g " +
            "WHERE (:userName is null or lower(u.userName) like %:userName%) "+
            "AND (:name is null or lower(u.name) like %:name%) "+
            "AND (:mobile is null or lower(u.mobile) like %:mobile%) "+
            "AND (:email is null or lower(u.email) like %:email%) "+
            "AND (:unit is null or lower(u.unit) like %:unit%) "+
            "AND (:groupPrivilege is null or lower(g.groupName) like %:groupPrivilege%) "+
            "AND (:damLake is null or lower(u.damLakes) like %:damLake%) "+
            "AND (:station is null or lower(u.stations) like %:station%) "+
            "AND (:statusId is null or u.statusId =:statusId )"+
            "AND (:fromDate is null or u.createdDate >= :fromDate) " +
            "AND (:toDate is null or u.createdDate <= :toDate) " +
            "AND (:gender is null or u.gender = :gender) "
    )
    Page<UserInfo> findAll(@Param("userName") String userName,
                           @Param("name") String name,
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
    @Query("select new com.neo.kttvapi.dto.UserInfoListDto(u.id, u.name, u.mobile, u.email,u.userName) from UserInfo u")
    List<UserInfoListDto> getListUsers();

    Optional<UserInfo> findById(Integer id);
}
