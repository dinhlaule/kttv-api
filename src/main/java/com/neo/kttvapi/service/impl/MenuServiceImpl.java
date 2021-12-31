package com.neo.kttvapi.service.impl;

import com.neo.kttvapi.dto.MenuListDto;
import com.neo.kttvapi.entity.Menu;
import com.neo.kttvapi.repository.MenuRepository;
import com.neo.kttvapi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private MenuRepository menuRepository;

    @Autowired
    public void setMenuRepository(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }


    @Override
    public List<MenuListDto> getAllDataMenu() {
        return menuRepository.menuListDto();
    }

    @Override
    public List<String> getMenuName() {
        List<String> list = new ArrayList();
        list.add("");
        list.addAll(menuRepository.getMenuName());
        return list;
    }

    @Override
    public Menu createNewMenu(Menu menu) {
            menu.setCreatedDate(new Date());
//            menu.setCreatedUser("Administrator");
            menuRepository.save(menu);
        return menu;
    }

    @Override
    public void updateMenu(MenuListDto menuListDto) {
            menuRepository.updateMenuById(menuListDto.getMenuid(), menuListDto.getName(), menuListDto.getParentId(),
                    menuListDto.getPictureFile(),menuListDto.getDetailsFile(),menuListDto.getMenuLevel(), menuListDto.getMenuPriority(), Integer.parseInt(menuListDto.getStatus()));
        }
}
