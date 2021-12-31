package com.neo.kttvapi.repository;

import com.neo.kttvapi.dto.MenuListDto;
import com.neo.kttvapi.dto.MenuListHistoryDto;
import com.neo.kttvapi.entity.MenuActionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuActHistoryRepository extends JpaRepository<MenuActionHistory,Long> {
    @Query("select new com.neo.kttvapi.dto.MenuListHistoryDto(m1.menuId,m1.menuName,m1.parentId,m2.name,m1.pictureFile,m1.detailFile,m1.menuLevel,m1.menuPriority,m1.createdUser,m1.createdDate,m1.status" +
            ", m1.updatedUser,m1.updatedDate) from MenuActionHistory m1 LEFT join Menu m2 on m1.parentId = m2.id " +
            " where m1.menuId = ?1")
     List<MenuListHistoryDto> menuListHistoryDto(Long id);
}
