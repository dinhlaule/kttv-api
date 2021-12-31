package com.neo.kttvapi.repository;

import com.neo.kttvapi.dto.GroupPrivilegeDto;
import com.neo.kttvapi.entity.GroupPrivilegeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupPrivilegeHistoryRepository extends JpaRepository<GroupPrivilegeHistory, Integer> {
    @Query("select new com.neo.kttvapi.dto.GroupPrivilegeDto( " +
            "gp.id, gp.groupName, gp.description, gp.menuId, m.name, gp.action, gp.status, " +
            "gp.createdDate, gp.createdBy, gp.updatedDate, gp.updatedBy) " +
            "from GroupPrivilegeHistory gp left join Menu m " +
            "on gp.menuId = m.id " +
            "where gp.groupId = ?1 " +
            "order by (CASE WHEN gp.updatedDate IS NULL THEN 0 ELSE 1 END) DESC, gp.updatedDate DESC")
    List<GroupPrivilegeDto> getHistory(Integer groupId);
}
