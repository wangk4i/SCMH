package com.hyd.resultdeal.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlTemplate {

    public Connection getConnection() {
        Connection conn=null;
        try {
            //加载数据库驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("数据库驱动加载成功！！");
            //定义连接数据库的url
            String url = "jdbc:sqlserver://172.16.0.21;DatabaseName=sc_mh2020070100";
            //定义连接数据库的用户名
            String userName = "scuser";
            //定义连接数据库的密码
            String passWord = "Hyd123";
            conn = DriverManager.getConnection(url, userName, passWord);
            System.out.println("已成功地与SQLServer数据库建立连接！！");
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }
}
