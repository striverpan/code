package com.pan.spark.base

import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.slf4j.LoggerFactory

/**
  * Created by pan on 2017/7/19.
  */
object RddOperate {

  def main(args: Array[String]): Unit = {

      val logger = LoggerFactory.getLogger(RddOperate.getClass)
      Logger.getLogger("org").setLevel(Level.INFO)
      val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("s"))
      /*val b1 = Array(("tom", true), ("bob", false))
      val b2 = Array(("bob", "xx"), ("tom", "xx"))
      val s1: RDD[(String, Boolean)] = sc.parallelize(b1)
      val s2: RDD[(String, String)] = sc.parallelize(b2)
      s2.leftOuterJoin(s1).collect().foreach(println(_))
      */
      val array: Array[(String, Int)] = Array(("spark",10),("hadoop",12),("spark",2),("hadoop",4))
      val bookRdd: RDD[(String, Int)] = sc.parallelize(array)
      val keyRdd: RDD[(String, Int)] = bookRdd.reduceByKey((a, b)=>{
        a+b
      })
      keyRdd.foreach(x => println("*******"+x._1+"#####"+x._2+"**************"))


  }
}
