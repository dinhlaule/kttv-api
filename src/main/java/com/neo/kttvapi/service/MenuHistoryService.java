package com.neo.kttvapi.service;



import com.neo.kttvapi.dto.MenuListHistoryDto;
import com.neo.kttvapi.entity.Menu;
import com.neo.kttvapi.entity.MenuActionHistory;

import java.util.List;

public interface MenuHistoryService {

    List<MenuListHistoryDto> getHistoryDataMenuById(Long id);

    MenuActionHistory createNewHistory(MenuActionHistory menuActionHistory);
}
