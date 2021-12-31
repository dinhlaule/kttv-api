package com.neo.kttvapi.service;

import com.neo.kttvapi.config.MailConfig;
import com.neo.kttvapi.dto.ReportDto;
import com.neo.kttvapi.repository.LogMessageRepository;
import com.neo.kttvapi.repository.MailReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@Service("SendMailScheduler")
public class ReportService {

    @Autowired
    private MailReportRepository mailReportRepository;

    @Autowired
    private LogMessageRepository logMessageRepository;

    @Autowired
    private MailConfig mailConfig;

    public void sendEmail() throws Exception {
        List<ReportDto> listMail = mailReportRepository.getListMail();
        for (int i = 0; i < listMail.size(); i++) {
            if (listMail.get(i).getSendEmail() == 1) {
                // Recipient's email ID needs to be mentioned.
                String to = listMail.get(i).getEmail();

                // Sender's email ID needs to be mentioned
                String from = "c2.monre.nc1@gmail.com";

                // Assuming you are sending email from through gmails smtp
                String host = "smtp.gmail.com";

                // Get system properties
                Properties properties = System.getProperties();

                // Setup mail server
                properties.put("mail.smtp.host", host);
                properties.put("mail.smtp.port", "465");
                properties.put("mail.smtp.ssl.enable", "true");
                properties.put("mail.smtp.auth", "true");

                // Get the Session object and username and password
                Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("c2.monre.nc1@gmail.com", "c2.monre.nc1");
                    }

                });

                // Used to debug SMTP issues
                session.setDebug(true);

                try {
                    // Create a default MimeMessage object
                    MimeMessage message = new MimeMessage(session);

                    // Set From: header field of the header
                    message.setFrom(new InternetAddress(from));

                    // Set To: header field of the header
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                    // Set Subject: header field
                    message.setSubject(listMail.get(i).getEmailTitle());

                    // Now set the actual message
                    message.setText(mailConfig.contentEmail(listMail.get(i).getUserName(), listMail.get(i).getEmailContent(), listMail.get(i).getParaName(), listMail.get(i).getCreatedDate() + "", listMail.get(i).getCreatedDate() + ""));

                    System.out.println("Sending...");
                    // Send message
                    Transport.send(message);
                    System.out.println("Sent message successfully...");
                } catch (MessagingException mex) {
                    mex.printStackTrace();
                }
            } else continue;
        }
    }

//    public void sendSms() {
//        List<ReportDto> listSms = reportRepository.getListReport();
//        for (int i = 0; i < listSms.size(); i++) {
//            if (listSms.get(i).getSendSms() == 1) {
//                Date date = new Date();
//                long time = date.getTime();
//                Timestamp timeSend = new Timestamp(time);
//                Timestamp timeFinish = new Timestamp(time + 3000);
//                logMessageRepository.save(new MessageLog(logMessageRepository.getMaxID() + 1,"admin","0123456789","just test!", timeSend, timeFinish, "lauld", 1, ""));
//            } else continue;
//        }
//    }
}
