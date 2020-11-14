package com.blockwit.kafka.security.examples;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Map;
import java.util.Properties;

public class SimpleProducer {

    private static Producer<Long, String> createProducer(Map<String, String> inProps, String server) {
        final Properties props = new Properties();

        props.putAll(inProps);

        props.put("enable.auto.commit", "false");
        props.put("session.timeout.ms", "30000");
        props.put("max.poll.records", "100");


        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                server);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());

        final Producer<Long, String> producer =
                new KafkaProducer<>(props);

        return producer;
    }

    static void runProducer(Map<String, String> inProps, String server, String topicName) throws InterruptedException {
        final Producer<Long, String> producer = createProducer(inProps, server);

        for (int i = 0; i < 10000000; i++) {

            try {
                RecordMetadata meat = (RecordMetadata) producer.send(new ProducerRecord(topicName, "msg" + i)).get();
                if (!meat.hasOffset()) {
                    throw new Exception("Record has not posted to topic");
                } else {
                    System.out.println("Message sends " + i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        producer.close();
        System.out.println("DONE");
    }

}
