package com.pan.spark.sql

import java.util.Properties

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by K0260006 on 2017/8/7.
  */
object SparkOracle {


  def getData(): List[String] = {

    val conf: SparkConf = new SparkConf().setAppName("sparksql-oracle").setMaster("local[*]")
    val properties = new Properties()
    properties.put("user", "siebel")
    properties.put("password", "siebel")
    properties.put("driver", "oracle.jdbc.driver.OracleDriver")
    val url = "jdbc:oracle:thin:@192.168.18.6:1526:siebsit"
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val jdbcDF = sqlContext.read.jdbc(url, "ACRM_RIGHTS_INFO", properties)
    jdbcDF.select("*").toJSON.foreach(x => println(x))

    return List[String]();

  }


  def main(args: Array[String]): Unit = {


    getData()

  }

}
