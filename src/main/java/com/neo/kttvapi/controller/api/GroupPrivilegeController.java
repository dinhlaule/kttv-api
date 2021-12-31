package com.neo.kttvapi.controller.api;

import com.neo.kttvapi.common.constant.ApplicationApi;
import com.neo.kttvapi.common.enums.ResponseCodeEnum;
import com.neo.kttvapi.controller.response.ResponseBodyDto;
import com.neo.kttvapi.dto.GroupPrivilegeDto;
import com.neo.kttvapi.entity.GroupPrivilege;
import com.neo.kttvapi.entity.GroupValueSystem;
import com.neo.kttvapi.entity.Menu;
import com.neo.kttvapi.service.GroupPrivilegeExcelExporter;
import com.neo.kttvapi.service.GroupPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(ApplicationApi.API_PREFIX + "/group-privilege")
@CrossOrigin(origins = "*")
public class GroupPrivilegeController {
    private GroupPrivilegeService groupPrivilegeService;

    @Autowired
    public GroupPrivilegeController(GroupPrivilegeService groupPrivilegeService) {
        this.groupPrivilegeService = groupPrivilegeService;
    }

    @GetMapping
    public ResponseEntity<List<GroupPrivilegeDto>> getAll(){

        return ResponseEntity.ok(groupPrivilegeService.getAll());
    }

    @PostMapping
    public ResponseEntity<GroupPrivilegeDto> add(@RequestBody @Valid GroupPrivilegeDto groupPrivilegeDto){
        groupPrivilegeService.add(groupPrivilegeDto);
        return new ResponseEntity<>(groupPrivilegeDto, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<GroupPrivilegeDto> update(@RequestBody @Valid GroupPrivilegeDto groupPrivilegeDto, @PathVariable Integer id) {
        return new ResponseEntity<>(groupPrivilegeService.update(groupPrivilegeDto, id), HttpStatus.OK);
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<List<GroupPrivilegeDto>> getHistory(@PathVariable Integer id){

        return ResponseEntity.ok(groupPrivilegeService.getHistory(id));
    }

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=group-privilege_" + currentDateTime + ".xlsx";
        response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
        response.setHeader(headerKey, headerValue);

        List<GroupPrivilegeDto> groupPrivilegeDtoList = groupPrivilegeService.getAll();

        GroupPrivilegeExcelExporter excelExporter = new GroupPrivilegeExcelExporter(groupPrivilegeDtoList);

        excelExporter.export(response);
    }

    @GetMapping(path = "/menu")
    public List<Menu> getMenus(){
        return groupPrivilegeService.getMenus();
    }

    @GetMapping(path = "/action")
    public List<GroupValueSystem> getActions(){
        return groupPrivilegeService.getActions();
    }

    @GetMapping("/findAll")
    public ResponseEntity<ResponseBodyDto<GroupPrivilegeDto>> findAll(){
        return new ResponseEntity<>(new ResponseBodyDto<>(groupPrivilegeService.findAll(),ResponseCodeEnum.R_200,"Ok",groupPrivilegeService.findAll().size()),HttpStatus.OK);
    }
}
