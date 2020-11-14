package com.blockwit.kafka.security.examples;

public class SimpleProducerTest_SASL_PLAINTEXT_PLAIN {

    public static void main(String[] args) throws InterruptedException {
        SimpleProducer.runProducer(Helper.of("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required serviceName=\"Kafka\" username=\"alice\" password=\"alice-secret\";",
                "sasl.mechanism", "PLAIN",
                "security.protocol", "SASL_PLAINTEXT"),
                Constants.HOSTNAME + ":9093", Constants.TOPIC_NAME);
    }

}
