package com.neo.kttvapi.controller.api;


import com.neo.kttvapi.common.constant.ApplicationApi;
import com.neo.kttvapi.dto.MenuListDto;
import com.neo.kttvapi.dto.MenuListHistoryDto;
import com.neo.kttvapi.entity.Menu;
import com.neo.kttvapi.entity.MenuActionHistory;
import com.neo.kttvapi.service.MenuExportExcel;
import com.neo.kttvapi.service.MenuHistoryService;
import com.neo.kttvapi.service.MenuService;
import com.neo.kttvapi.service.impl.MenuHistoryServiceImpl;
import com.neo.kttvapi.service.impl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(ApplicationApi.API_PREFIX +"/menu")
@CrossOrigin(origins = "*")
public class MenuController {

    @Autowired
    private MenuServiceImpl menuServiceImpl;

    @Autowired
    private MenuHistoryServiceImpl menuHistoryService;

    @GetMapping("/getallmenu")
    public List<MenuListDto> getAllDataMenu(){
        return menuServiceImpl.getAllDataMenu();
    }

    @GetMapping("/getmenuname")
    public List<String> getMenuName(){
        return menuServiceImpl.getMenuName();
    }

    @PostMapping("/addnewmenu")
    public Menu createNewMenu( @Valid @RequestBody Menu menu){
        return menuServiceImpl.createNewMenu(menu);
    }

    @PutMapping("/update/{id}")
    public String updateMenu(@Valid @RequestBody MenuListDto menuListDto, @PathVariable Long id) {
        if(menuListDto.getMenuid().equals(id)) {
            menuServiceImpl.updateMenu(menuListDto);
            MenuActionHistory menuActionHistory = new MenuActionHistory();
            menuActionHistory.setMenuId(menuListDto.getMenuid());
            menuActionHistory.setMenuName(menuListDto.getName());
            menuActionHistory.setParentId(menuListDto.getParentId());
            menuActionHistory.setPictureFile(menuListDto.getPictureFile());
            menuActionHistory.setDetailFile(menuListDto.getDetailsFile());
            menuActionHistory.setMenuLevel(menuListDto.getMenuLevel());
            menuActionHistory.setMenuPriority(menuListDto.getMenuPriority());
            menuActionHistory.setCreatedUser(menuListDto.getCreatedUser());
            menuActionHistory.setCreatedDate(menuListDto.getCreatedDate());
            menuActionHistory.setStatus(Integer.parseInt(menuListDto.getStatus()));
            menuHistoryService.createNewHistory(menuActionHistory);
            return "update thanh cong!";
        } else {
            return "Update khong thanh cong!";
        }
    }

    @GetMapping("/history/menu/{id}")
    public List<MenuListHistoryDto> getHistoryEdit(@PathVariable Long id){
        return menuHistoryService.getHistoryDataMenuById(id);
    }

//    @GetMapping("/export/excel")
//    public void exportToExcel(HttpServletResponse response) throws IOException {
//        response.setContentType("application/octet-stream");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-disposition";
//        String headerValue = "attachment; filename=menuList_" + currentDateTime + ".xlsx";
//        response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
//        response.setHeader(headerKey, headerValue);
//        List<MenuListDto> listMenu = menuServiceImpl.getAllDataMenu();
//
//        MenuExportExcel excelExporter = new MenuExportExcel(listMenu);
//
//        excelExporter.export(response);
//    }

}
