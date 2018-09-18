package com.pan.spark.util

import java.sql.{Connection, ResultSet, SQLException, Statement}

import com.pan.system.SystemException
import com.pan.util.mysql.DataBaseConPool
import org.apache.spark.rdd.RDD

import scala.runtime.Nothing$

/**
  * Created by pan on 2017/9/11.
  */
class MysqlHelper[T] {


  def query(sql: String): ResultSet = {
    val con = MysqlDBConPool.getMysqlConnectionPool.getConnection
    try {
      val stmt = con.createStatement()
      stmt.executeQuery(sql)
    } catch {
      case e: Exception => e.printStackTrace() ;throw e
    } finally {
      con.close()
    }

  }

  def insert(sql:String): Unit = {
    val con = DataBaseConPool.getConnnection
    try {
      val stmt = con.createStatement
      val result = stmt.executeUpdate(sql)
      return result
    } catch {
      case e: SQLException => throw new SystemException("插入数据失败" + sql, e)
    } finally {
      con.close()
    }
  }


}
