package com.neo.kttvapi.repository;

//import com.neo.kttvapi.dto.ReportDto;
//import com.neo.kttvapi.entity.EmailSms;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;

//import java.util.List;

//@Repository
//public interface ReportRepository extends JpaRepository<EmailSms, Integer> {
////    @Query("select new com.neo.kttvapi.dto.ReportDto( " +
////            "gc.id, gc.groupUserReceiveData, gc.sendEmail, gc.sendSms, ui.name, ui.email, ui.mobile, es.emailTitle, es.emailContent, gvs.paraName, " +
////            "es.createdDate, sf.sendFre, sf.sendFreUnit, es.smsContent, es.status) " +
////            "from GroupConfig gc left join GroupUserSystem gus " +
////            "on gc.id = gus.id left join UserInfo ui on gus.id = ui.id left join EmailSms es on gc.sendList = es.id left join SendFrequency sf " +
////            "on gc.id = sf.id left join GroupValueSystem gvs on gc.groupName = gvs.groupName")
////    List<ReportDto> getListReport();
//}

import com.neo.kttvapi.dto.ReportDto;
import com.neo.kttvapi.utils.UtilsMail;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MailReportRepositoryImpl implements MailReportRepository {
    @Autowired
    private HikariDataSource ds;

    @Autowired
    private UtilsMail utils;

    @Value("${app.send-mail.get-mail}")
    private String sqlListMail;

    @Value("${app.send-mail.update-mail}")
    private String sqlUpdateMail;

    @Override
    public List<ReportDto> getListMail() throws Exception {
        Connection conn = null;
        List<ReportDto> list = new ArrayList<>();
        try {
            conn = ds.getConnection();
            ResultSet resultSet = utils.CallJBDC( sqlListMail, conn);
            while (resultSet.next()) {
                ReportDto mail = new ReportDto();
                mail.setId(resultSet.getInt(1));
                mail.setGroupUserReceiveData(resultSet.getString(2));
                mail.setSendEmail(resultSet.getInt(3));
                mail.setSendSms(resultSet.getInt(4));
                mail.setUserName(resultSet.getString(5));
                mail.setEmail(resultSet.getString(6));
                mail.setMobile(resultSet.getString(7));
                mail.setEmailTitle(resultSet.getString(8));
                mail.setEmailContent(resultSet.getString(9));
                mail.setParaName(resultSet.getString(10));
                mail.setCreatedDate(resultSet.getDate(11));
                mail.setSendFre(resultSet.getInt(12));
                mail.setSendFreUnit(resultSet.getString(13));
                mail.setSmsContent(resultSet.getString(14));
                mail.setStatus(resultSet.getInt(15));
                list.add(mail);
            }
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception var11) {}
            }
        }
        return list;
    }

    @Override
    public String updateMail(int id) throws Exception {
        return null;
    }
}
