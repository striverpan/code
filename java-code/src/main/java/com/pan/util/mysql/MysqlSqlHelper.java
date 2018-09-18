package com.pan.util.mysql;

import com.pan.system.SystemException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.ResultSetDynaClass;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by K0260006 on 2017/9/5.
 */
public class MysqlSqlHelper<T> {


    public List<T> query(String sql, Class<T> c) {
        List resList = new ArrayList<T>();
        Connection con = DataBaseConPool.getConnnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            stmt = con.prepareStatement(sql);
            result = stmt.executeQuery(sql);
            ResultSetDynaClass rsdc = new ResultSetDynaClass(result);
            Iterator rows = rsdc.iterator();
            while (rows.hasNext()) {
                T bean = c.newInstance();
                DynaBean row = (DynaBean) rows.next();
                BeanUtils.copyProperties(bean, row);
                resList.add(bean);
            }
        } catch (Exception e) {
            throw new SystemException("查询数据失败"+sql,e);
        } finally {
            close(con);
        }
        return resList;
    }

    public int insert(String sql) {
        Connection con = DataBaseConPool.getConnnection();
        Statement stmt = null;
         int result = 0;
        try {
            stmt = con.createStatement();
            result = stmt.executeUpdate(sql);
            return result;
        } catch (SQLException e) {
            throw new SystemException("插入数据失败:"+sql,e);
        } finally {
            close(con);
        }
    }

    public void close(Connection con) {

        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new MysqlSqlHelper().insert("insert into sparkwordcount(word,count) values(" + "'w'" + "," + 10 + ")");
    }

}
