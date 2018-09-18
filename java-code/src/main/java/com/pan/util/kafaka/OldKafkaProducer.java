package com.pan.util.kafaka;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * Created by pan on 2017/8/29.
 */
public class OldKafkaProducer extends Thread {
    private final kafka.javaapi.producer.Producer<Integer, String> producer;
    private final String topic;
    private final Properties props = new Properties();

    public OldKafkaProducer(String topic) {
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", "127.0.0.1:9092");
        producer = new kafka.javaapi.producer.Producer<Integer, String>(new ProducerConfig(props));
        this.topic = topic;
    }

    @Override
    public void run() {
        int messageNo = 1;
        while (true) {
            String messageStr = new String("Message_" + messageNo);
            System.out.println("Send:" + messageStr);
            producer.send(new KeyedMessage<Integer, String>(topic, messageStr));
            messageNo++;
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
