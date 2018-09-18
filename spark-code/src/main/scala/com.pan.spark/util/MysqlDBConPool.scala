package com.pan.spark.util

import java.sql.Connection
import java.util.ResourceBundle

import com.mchange.v2.c3p0.ComboPooledDataSource
import com.pan.spark.common.SystemException

/**
  * Created by pan on 2017/9/11.
  */
class MysqlDBConPool extends Serializable {

  private val cpds: ComboPooledDataSource = new ComboPooledDataSource(true)

   {
    val mysqlrb = ResourceBundle.getBundle("config/mysql");
    val c3p0rb = ResourceBundle.getBundle("config/c3p0")

    val driver: String = mysqlrb.getString("jdbc.driver")
    val host: String = mysqlrb.getString("jdbc.host")
    val port: String = mysqlrb.getString("jdbc.port")
    val database: String = mysqlrb.getString("jdbc.database")
    val username: String = mysqlrb.getString("jdbc.username")
    val password: String = mysqlrb.getString("jdbc.password")

    val maxPoolSize: Int = c3p0rb.getString("c3p0.maxPoolSize").toInt
    val minPoolSize: Int = c3p0rb.getString("c3p0.minPoolSize").toInt
    val acquireIncrement: Int = c3p0rb.getString("c3p0.maxPoolSize").toInt
    val initialPoolSize: Int = c3p0rb.getString("c3p0.initialPoolSize").toInt
    val maxIdleTime: Int = c3p0rb.getString("c3p0.maxIdleTime").toInt

    try {
      cpds.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database + "?useUnicode=true&amp;characterEncoding=UTF-8")
      cpds.setDriverClass("com.mysql.jdbc.Driver");
      cpds.setUser(username);
      cpds.setPassword(password)
      cpds.setMaxPoolSize(maxPoolSize)
      cpds.setMinPoolSize(minPoolSize)
      cpds.setAcquireIncrement(acquireIncrement)
      cpds.setInitialPoolSize(initialPoolSize) //初始创建
      cpds.setMaxIdleTime(maxIdleTime) //最大空闲时间
    } catch {
      case e: Exception => throw new SystemException("thread:" + Thread.currentThread().getId + "-->" + "连接池初始化异常", e)
    }
  }


  def getConnection: Connection = {
    try {
      return cpds.getConnection();
    } catch {
      case e: Exception => throw new SystemException("获取连接池失败", e)
    }
  }

}

object MysqlDBConPool {

  private var mysqlConnectionPool: MysqlDBConPool = _

  def getMysqlConnectionPool: MysqlDBConPool = {
    synchronized {
      if (mysqlConnectionPool == null) {
        mysqlConnectionPool = new MysqlDBConPool
      }
    }
    mysqlConnectionPool
  }

}
