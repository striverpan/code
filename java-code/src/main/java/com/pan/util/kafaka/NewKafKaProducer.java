package com.pan.util.kafaka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by pan on 2017/8/30.
 */
public class NewKafKaProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "datanode1.shmetrotest.com:9092,datanode2.shmetrotest.com:9092,datanode3.shmetrotest.com:9092");
        props.put("acks", "all");
        props.put("retries", 1);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        String str = "{\"SignalData\":{\"c_p1_897_3\":\"1\",\"c_p1_17_0\":\"10\",\"c_p1_897_0\":\"0\",\"c_p1_15_0\":\"12\",\"c_p1_897_2\":\"0\",\"c_p1_897_1\":\"0\",\"c_p1_11_0\":\"1640\"},\"signalData\":{\"$ref\":\"$.SignalData\"},\"trainInfo\":{\"train_type_no\":\"03A02\",\"train_type_name\":\"车型03A02\",\"train_name\":\"列车0335\",\"train_no\":\"0335\",\"line_name\":\"3号线\",\"line_id\":\"3\"}}";
        Producer<String, String> producer = new KafkaProducer(props);
        int i=0;
        while (true){
            System.out.println(i);
            producer.send(new ProducerRecord<String, String>("rule_17_88_1514083958942", Integer.toString(i), str));
            i++;
        }
        //producer.close();
    }
}
