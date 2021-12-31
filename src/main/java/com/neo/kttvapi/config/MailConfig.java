package com.neo.kttvapi.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {

    public String contentEmail(String nameUser, String contentUserInput, String paraName, String fromDate, String toDate) {
        String content = "Dear " + nameUser + ",\n" + contentUserInput + "\n" + "Chúng tôi gửi đến bạn " + paraName + " từ " + fromDate +
                " đến " + toDate + ".";
        return content;
    }
}
