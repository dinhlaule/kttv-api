package com.neo.kttvapi.repository;

import com.neo.kttvapi.dto.GroupPrivilegeDto;
import com.neo.kttvapi.entity.GroupPrivilege;
import com.neo.kttvapi.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupPrivilegeRepository extends JpaRepository<GroupPrivilege, Integer> {
    @Query("select new com.neo.kttvapi.dto.GroupPrivilegeDto( " +
            "gp.id, gp.groupName, gp.description, gp.menuId, m.name, gp.action, gp.status, " +
            "gp.createdDate, gp.createdBy, gp.updatedDate, gp.updatedBy) " +
            "from GroupPrivilege gp left join Menu m " +
            "on gp.menuId = m.id " +
            "order by (CASE WHEN gp.createdDate IS NULL THEN 0 ELSE 1 END) DESC, gp.createdDate DESC")
    List<GroupPrivilegeDto> getAll();

    @Query("select new com.neo.kttvapi.entity.Menu(m.id,m.name,m.parentId,m.pictureFile,m.detailsFile,m.menuLevel,m.menuPriority,m.status,m.createdDate,m.createdUser) " +
            "             from Menu m")
    List<Menu> getMenus();

    GroupPrivilege getGroupPrivilegeById(Integer id);
}
