package com.pan.util.kafaka;

/**
 * Created by pan on 2017/8/29.
 */
public class KafkaTest {
    public static void main(String[] args) {

        OldKafkaProducer producerThread = new OldKafkaProducer(KafkaProperties.topic);
        producerThread.start();
   /*   OldKafkaConsumer consumerThread = new OldKafkaConsumer(KafkaProperties.topic);
        consumerThread.start();*/

    }
}
