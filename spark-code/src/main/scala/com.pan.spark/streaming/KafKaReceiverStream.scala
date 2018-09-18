package com.pan.spark.streaming

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka._
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by pan on 2017/8/30.
  */

object KafKaReceiverStream {


  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.INFO)
    val conf = new SparkConf().setAppName("spark-stream").setMaster("local[*]")
    val ssc = new StreamingContext(conf, Seconds(10))
    ssc.checkpoint("./checkpoint")
    val Array(zkQuorum, topics, group, numThreads) = Array("127.0.0.1:2181", "pan", "c1", "2")
    val topicMap = topics.split(",").map((_, numThreads.toInt)).toMap
    val message: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap)
    val lines: DStream[String] = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap).map(_._2)
    val wordCounts = lines.flatMap(_.split(" ")).map((_, 1))
    // 定义更新状态方法，参数values为当前批次单词频度，state为以往批次单词频度
    /*val updateFunc = (values: Seq[Int], state: Option[Int]) => {
      val currentCount = values.foldLeft(0)(_ + _)
      val previousCount = state.getOrElse(0)
      Some(currentCount + previousCount)
    }
    val stateDstream =wordCounts.updateStateByKey[Int](updateFunc);
    stateDstream.print()*/


    wordCounts.print()

    ssc.start()
    ssc.awaitTermination()

  }
}
