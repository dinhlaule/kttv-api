package com.neo.kttvapi.repository;

import com.neo.kttvapi.dto.MenuListDto;
import com.neo.kttvapi.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {

    @Query("select new com.neo.kttvapi.dto.MenuListDto(m1.id,m1.name,m1.parentId,m2.name,m1.pictureFile,m1.detailsFile,m1.menuLevel,m1.menuPriority,m1.createdUser,m1.createdDate,m1.status) " +
            "from Menu m1 left join Menu m2 on (m1.parentId = m2.id)")
    List<MenuListDto> menuListDto();

    @Query("select distinct m.id,m.name from Menu m")
    List<String> getMenuName();

    @Transactional
    @Modifying
    @Query("update Menu m set m.name = ?2, m.parentId = ?3,m.pictureFile = ?4, m.detailsFile = ?5, m.menuLevel = ?6,m.menuPriority = ?7, " +
            "m.status = ?8 where m.id = ?1")
    public Integer updateMenuById(Long id, String name, Long parentId, String pictureFile,String detailsFile,Integer menuLevel,Integer menuPriority,Integer status);

    Menu getMenuByIdAndStatusAndMenuLevel(Long id,Integer status, Integer menuLevel);

    List<Menu> getMenusByParentIdAndStatusAndMenuLevel(Long id,Integer status,Integer menuLevel);
}
