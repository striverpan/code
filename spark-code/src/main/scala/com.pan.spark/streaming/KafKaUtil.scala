package com.pan.spark.streaming

import java.util
import java.util.{Arrays, HashMap}
import scala.math
import org.apache.kafka.clients.consumer.{ConsumerRecord, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

import scala.util.Random

/**
  * Created by pan on 2017/8/27.
  */
object KafKaUtil {

  def getProducer(): KafkaProducer[String, String] = {

    val Array(brokers, topic, messagesPerSec, wordsPerMessage) = Array("127.0.0.1:9092", "test", "1", "10")
    val props = new HashMap[String, Object]()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer")
    new KafkaProducer[String, String](props)
  }

  def sendKakfaMessage(): Unit = {

    new Thread(new Runnable {
      override def run(): Unit = {
        val Array(brokers, topics, messagesPerSec, wordsPerMessage) = Array("127.0.0.1:9092", "test,pan", "1", "10")
        val producer = getProducer()

        while (true) {
          (1 to messagesPerSec.toInt).foreach { messageNum =>
            val str = (1 to wordsPerMessage.toInt).map(x => scala.util.Random.nextInt(10).toString).mkString(" ")
            println("send"+str)
            val i = (math.random*10).toInt%2
            val topic =topics.split(",")(i)
            val message = new ProducerRecord[String, String](topic, null, str)
            producer.send(message)
          }
          Thread.sleep(100)
        }
      }
    }).start
  }

  def getCusumer(): KafkaConsumer[String, String] = {

    val props = new HashMap[String, Object]()
    props.put("bootstrap.servers", "127.0.0.1:9092")
    props.put("group.id", "pan")
    props.put("enable.auto.commit", "true")
    props.put("auto.commit.interval.ms", "1000")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    new org.apache.kafka.clients.consumer.KafkaConsumer[String, String](props)
  }

  def customeMessage(): Unit = {
    new Thread(new Runnable {
      override def run(): Unit = {
        val consumer = getCusumer()
        consumer.subscribe(Arrays.asList("test"))
        while (true) {
          val records: ConsumerRecords[String, String] = consumer.poll(100)
          val it: util.Iterator[ConsumerRecord[String, String]] = records.iterator()
          while (it.hasNext) {
            println( "custom"+it.next().value())
          }
        }
      }
    }).start
  }


  def main(args: Array[String]): Unit = {
    sendKakfaMessage()
    //customeMessage()
  }

}
