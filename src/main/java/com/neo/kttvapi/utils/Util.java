package com.neo.kttvapi.utils;

import com.neo.kttvapi.entity.LogsActionDetail;

import javax.persistence.Column;
import java.time.Instant;

public class Util {
    public static LogsActionDetail setLogsActionDetail(String menuName, String action, String userAct, String tableName, String content){
        LogsActionDetail logsActionDetail=new LogsActionDetail();
        logsActionDetail.setAction(action);
        logsActionDetail.setUserAct(userAct);
        logsActionDetail.setTableName(tableName);
        logsActionDetail.setDateAct(Instant.now());
        logsActionDetail.setContent(content);
        return logsActionDetail;
    }
}
