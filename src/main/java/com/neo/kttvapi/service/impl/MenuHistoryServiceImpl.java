package com.neo.kttvapi.service.impl;

import com.neo.kttvapi.dto.MenuListHistoryDto;
import com.neo.kttvapi.entity.MenuActionHistory;
import com.neo.kttvapi.repository.MenuActHistoryRepository;
import com.neo.kttvapi.service.MenuHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuHistoryServiceImpl implements MenuHistoryService {

     private MenuActHistoryRepository menuActHistoryRepository;

    @Autowired
    public void setMenuActHistoryRepository(MenuActHistoryRepository menuActHistoryRepository) {
        this.menuActHistoryRepository = menuActHistoryRepository;
    }

    @Override
    public List<MenuListHistoryDto> getHistoryDataMenuById(Long id) {
        return menuActHistoryRepository.menuListHistoryDto(id);
    }

    @Override
    public MenuActionHistory createNewHistory(MenuActionHistory menuActionHistory) {
            menuActionHistory.setUpdatedDate(new Date());
//            menuActionHistory.setUpdatedUser("admin");
            menuActHistoryRepository.save(menuActionHistory);
        return menuActionHistory;
    }
}
