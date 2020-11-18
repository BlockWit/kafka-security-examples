package com.blockwit.kafka.security.examples.cookbook;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class JavaKafkaConsumerExample {

    public static void main(String[] args) {
        String server = "localhost:9092";
        String topicName = "test.topic";
        String groupName = "test.group";

        final Properties props = new Properties();

        props.put(ConsumerConfig.GROUP_ID_CONFIG,
                groupName);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                server);
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

        ConsumerRecords<Long, String> consumerRecords = consumer.poll(30000);
        if (!consumerRecords.isEmpty()) {
            System.out.println("SUCCESS");
            System.out.println(consumerRecords.iterator().next().value());
        }

        consumer.close();
    }

}
