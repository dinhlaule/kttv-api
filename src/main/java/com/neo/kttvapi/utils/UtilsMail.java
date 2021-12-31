package com.neo.kttvapi.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;
import org.springframework.stereotype.Service;

@Service
public class UtilsMail {

    public ResultSet CallJBDC(String sql, Connection conn, Object... params){
        CallableStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = conn.prepareCall(sql);
            statement.registerOutParameter(1, OracleTypes.CURSOR);
            if (params != null && params.length > 0) {
                for(int i = 0; i < params.length; ++i) {
                    statement.setObject(i + 2, params[i]);
                }
            }
            statement.execute();
            resultSet = (ResultSet) statement.getObject(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }
}
