package com.pan.spark.streaming.flume

import java.nio.ByteBuffer

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.flume._
import org.apache.spark.streaming.{Milliseconds, StreamingContext}
import org.slf4j.LoggerFactory

object Flume01 {

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.INFO)
    val logger = LoggerFactory.getLogger(Flume01.getClass)

    val Array(host, port) = Array("localhost", "44444")
    val conf = new SparkConf().setMaster("local[2]").setAppName("flume01")
    val ssc = new StreamingContext(conf, Milliseconds(2000))

    val stream = FlumeUtils.createStream(ssc, host, port.toInt, StorageLevel.MEMORY_ONLY_2)
    stream.foreachRDD(rdd => {
      if (!rdd.isEmpty()) {
      /*  rdd.foreachPartition(iterator => {
          if (iterator.nonEmpty) {
            while (iterator.hasNext) {
              val arrayByte: Array[Byte] = iterator.next().event.getBody.array()
              println("*******"+ new String(arrayByte)+"*******")
            }
          }
        })*/

        println("xxxxxxxxxx")
        //rdd.map(x => logger.info("***************"+x+"***************")).collect()
        rdd.map(x => println("***************"+new String(x.event.getBody.array())+"***************")).collect()
      }
    })
    //println("xxxxxxxx")
    //rdd.map(x => logger.info("***************"+x+"***************"))
    //rdd.map(x => println("***************"+x+"***************"))})


    //stream.count().map(cnt => "Received " + cnt + " flume events." ).print()
    /*val lineDstream: DStream[String] = stream.map(x => new String(x.event.getBody.array()))
     //6、切分每一行,每个单词计为1
     val wordAndOne: DStream[(String, Int)] = lineDstream.flatMap(_.split(" ")).map((_,1))
     //7、相同单词出现的次数累加
     val result: DStream[(String, Int)] = wordAndOne.reduceByKey(_+_)
     //8、打印输出
     result.print()*/

    ssc.start()
    ssc.awaitTermination()
  }


}
