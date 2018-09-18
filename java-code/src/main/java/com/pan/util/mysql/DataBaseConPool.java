package com.pan.util.mysql;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.pan.system.SystemException;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by K0260006 on 2017/9/5.
 */
public class DataBaseConPool {

    static ComboPooledDataSource cpds = null;

    public static void initDataSource() {
        //cpds = new ComboPooledDataSource("oracle");//这是oracle数据库
        cpds = new ComboPooledDataSource("mysql");// 这是mysql数据库

        ResourceBundle rb = ResourceBundle.getBundle("config/mysql");
        String driver = rb.getString("jdbc.driver");
        String host = rb.getString("jdbc.host");
        String port = rb.getString("jdbc.port");
        String database = rb.getString("jdbc.database");
        String username = rb.getString("jdbc.username");
        String password = rb.getString("jdbc.password");
        cpds.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database);//连接url

        System.out.println("jdbc:mysql://" + host + ":" + port + "/" + database);

        try {
            cpds.setDriverClass(driver);//数据库驱动
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            throw new SystemException("C3p0连接mysql找不到驱动", e);
        }
        cpds.setUser(username);//用户名
        cpds.setPassword(password);//密码

        ResourceBundle c3p0rb = ResourceBundle.getBundle("config/c3p0");
        String maxPoolSize = c3p0rb.getString("c3p0.maxPoolSize");
        String minPoolSize = c3p0rb.getString("c3p0.minPoolSize");
        String acquireIncrement = c3p0rb.getString("c3p0.maxPoolSize");
        String initialPoolSize = c3p0rb.getString("c3p0.initialPoolSize");
        String maxIdleTime = c3p0rb.getString("c3p0.maxIdleTime");

        cpds.setMaxPoolSize(Integer.valueOf(maxPoolSize));//连接池中保留的最大连接数
        cpds.setMinPoolSize(Integer.valueOf(minPoolSize));//连接池中保留的最小连接数
        cpds.setAcquireIncrement(Integer.valueOf(acquireIncrement));//一次性创建新连接的数目
        cpds.setInitialPoolSize(Integer.valueOf(initialPoolSize));//初始创建
        cpds.setMaxIdleTime(Integer.valueOf(maxIdleTime));//最大空闲时间
    }

    public static Connection getConnnection() {
        if (cpds == null) {
            initDataSource();
        }
        try {
            return cpds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
