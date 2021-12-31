package com.neo.kttvapi.repository;

import com.neo.kttvapi.dto.ReportDto;

import java.util.List;

public interface MailReportRepository {
    List<ReportDto> getListMail() throws Exception;

    String updateMail(int id) throws Exception;
}
