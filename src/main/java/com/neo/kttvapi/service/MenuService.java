package com.neo.kttvapi.service;

import com.neo.kttvapi.dto.MenuListDto;
import com.neo.kttvapi.entity.Menu;

import java.util.List;

public interface MenuService {

    List<MenuListDto> getAllDataMenu();

    List<String> getMenuName();

    Menu createNewMenu(Menu menu);

    void updateMenu(MenuListDto menuListDto);
}
