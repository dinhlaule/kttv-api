package com.neo.kttvapi.controller.api;

import com.neo.kttvapi.common.constant.ApplicationApi;
import com.neo.kttvapi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApplicationApi.API_PREFIX +"/email")
@CrossOrigin(origins = "*")
public class SendReportController {
    @Autowired
    private ReportService mailService;

    @GetMapping("/sendEmail")
    public void sendEmail() throws Exception {
        mailService.sendEmail();
    }

//    @GetMapping("/sendSms")
//    @Scheduled(fixedRate = 20000, initialDelay = 5000)
//    public void sendSms(){
//        mailService.sendSms();
//    }
}
