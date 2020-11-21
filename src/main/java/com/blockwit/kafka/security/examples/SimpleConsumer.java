package com.blockwit.kafka.security.examples;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class SimpleConsumer {

    private static Consumer<Long, String> createConsumer(Map<String, String> inProps, String server, String topicName, String groupName) {

        final Properties props = new Properties();

        props.putAll(inProps);

        props.put("enable.auto.commit", "false");
        props.put("auto.offset.reset", "earliest");
        props.put("max.poll.records", "1");

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                server);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,
                groupName);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());


        final Consumer<Long, String> consumer =
                new KafkaConsumer<>(props);

        TopicPartition tp = new TopicPartition(topicName, 0);
        List<TopicPartition> tps = Arrays.asList(tp);
        consumer.assign(tps);
        consumer.seekToBeginning(tps);

        return consumer;
    }

    static void runConsumer(Map<String, String> inProps, String server, String topicName, String groupName) {
        final Consumer<Long, String> consumer = createConsumer(inProps, server, topicName, groupName);

        final int giveUp = 100;
        int noRecordsCount = 0;

        while (true) {
            ConsumerRecords<Long, String> consumerRecords = null;

            try {
                consumerRecords = consumer.poll(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (consumerRecords.count() == 0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) break;
                else continue;
            }

            consumerRecords.forEach(record -> {
                System.out.printf("Consumer Record:(%d, %s, %d, %d)\n",
                        record.key(), record.value(),
                        record.partition(), record.offset());
            });

            consumer.commitAsync();
            break;
        }
        consumer.close();
    }

}
