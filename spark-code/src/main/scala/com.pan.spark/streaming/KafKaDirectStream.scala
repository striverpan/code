package com.pan.spark.streaming

import java.sql.Connection

import com.pan.spark.common.SystemException
import com.pan.spark.util.MysqlDBConPool
import kafka.serializer.StringDecoder
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.slf4j.LoggerFactory

/**
  * Created by pan on 2017/8/30.
  */
object KafKaDirectStream {


  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.INFO)
    // 类名.class
    val logger = LoggerFactory.getLogger(KafKaDirectStream.getClass)
    val Array(brokers, topics) = Array("127.0.0.1:9092", "pan")

    // Create context with 2 second batch interval
    val sparkConf = new SparkConf().setAppName("DirectKafkaWordCount").setMaster("local[2]")
    val ssc = new StreamingContext(sparkConf, Seconds(10))

    // Create direct kafka stream with brokers and topics
    val topicsSet = topics.split(",").toSet
    val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers)
    val messages: InputDStream[(String, String)] = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topicsSet)

    //updateStateByKey
    /* val lines = messages.map(_._2)
     val words = lines.flatMap(_.split(" "))

     val pairs = words.map((_,1))
     val wordCounts = pairs.updateStateByKey((values: Seq[Int], state: Option[Int]) => {
       var newValue = state.getOrElse(0)
       for (value <- values) {
         newValue += value
       }
       Option(newValue)
     })
     wordCounts.print()*/

    //transform操作
    /*val blackUserList = Array(("tom", true), ("bob", true))
    val blackUserListRDD = ssc.sparkContext.parallelize(blackUserList, 5)
    val validUsersDstream = messages.transform(usersListRDD => {
      val usersListRDDValue = usersListRDD.map(rdd => {
        val rddValue = (rdd._2)
        val values = rddValue.split(" ")
        (values(0), values.apply(1))
      }
      )
      val joinedRDD = usersListRDDValue.leftOuterJoin(blackUserListRDD)
      val filterRDD = joinedRDD.filter(tuple => {
        logger.error("user joined value" + (tuple) + " " + (tuple._2._1) + " " + (tuple._2._2))
        if (tuple._2._2 == Some(true)) {
          true
        } else {
          false
        }
      })
      val validUsers = filterRDD.map(tuple => tuple._1)
      validUsers.foreach(x => {
        logger.error("user " + x)
      })
      validUsers
    })
    validUsersDstream.print()
    */

    //insert result into mysql
    val lines = messages.map(x => x._2)
    val words = lines.flatMap(_.split(" "))
    val pairs = words.map((_, 1))
    pairs.foreachRDD(pairRdd => {
      pairRdd.foreachPartition(iter => {
        var con:Connection = null
        try {
          con = MysqlDBConPool.getMysqlConnectionPool.getConnection
          con.setAutoCommit(false)
          val stmt = con.createStatement()
          iter.foreach(line => {
            val sql = "insert into sparkwordcount(word,count) values(" + line._1 + "," + line._2 + ")"
            stmt.addBatch(sql)
          })
          stmt.executeBatch
          con.commit
        } catch {
           case e: SystemException => e.printStackTrace()
        } finally {
          if (con != null) {
            con.close()
          }
        }
      })
    })


    ssc.start()
    ssc.awaitTermination()

  }
}
