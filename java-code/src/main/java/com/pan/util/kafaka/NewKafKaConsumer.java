package com.pan.util.kafaka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.*;

/**
 * Created by pan on 2017/8/29.
 */
public class NewKafKaConsumer {

    public static void autoControlOffsetConsumer() {
        KafkaConsumer<String, String> consumer = getKafkaConsumer(true);
        consumer.subscribe(Arrays.asList("test"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                printRecord(record);
        }
    }

    private static void printRecord(ConsumerRecord<String, String> record) {
        System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value() + "\n");
    }


    /*
    手动控制Offset
     */
    public static void notAutoControlOffsetCustomer() {
        KafkaConsumer<String, String> consumer = getKafkaConsumer(false);
        consumer.subscribe(Arrays.asList("rule_17_35"));
        final int minBatchSize = 200;
        List<ConsumerRecord<String, String>> buffer = new ArrayList();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                printRecord(record);
                buffer.add(record);
            }
            if (buffer.size() >= minBatchSize) {
                //insertIntoDb(buffer);
                consumer.commitSync();
                buffer.clear();
            }
        }
    }

    /*
 针对每个分区控制Offset
  */
    public void controlPartitionOffset() {

        KafkaConsumer<String, String> consumer = getKafkaConsumer(false);
        consumer.subscribe(Arrays.asList("test"));
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
                for (TopicPartition partition : records.partitions()) {
                    List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                    for (ConsumerRecord<String, String> record : partitionRecords) {
                        printRecord(record);
                    }
                    long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                    consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
                }
            }
        } finally {
            consumer.close();
        }

    }

    private static KafkaConsumer<String, String> getKafkaConsumer(boolean autoCommitOffset) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.1.55.131:9092,10.1.55.132:9092,10.1.55.133:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", autoCommitOffset);
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return (KafkaConsumer<String, String>) new KafkaConsumer(props);
    }


    public static void main(String[] args) {

        notAutoControlOffsetCustomer();

    }

}