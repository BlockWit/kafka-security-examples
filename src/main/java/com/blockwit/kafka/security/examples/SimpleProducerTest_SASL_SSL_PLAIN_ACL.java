package com.blockwit.kafka.security.examples;

public class SimpleProducerTest_SASL_SSL_PLAIN_ACL {

    public static void main(String[] args) throws InterruptedException {
        SimpleProducer.runProducer(Helper.of(
                "sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required serviceName=\"Kafka\" username=\"alice\" password=\"alice-secret\";",
                "sasl.mechanism", "PLAIN",
                "security.protocol", "SASL_SSL",
                "ssl.truststore.location", SSLConstants.SSL_CLIENT_TRUSTSTORE_PATH,
                "ssl.truststore.password", SSLConstants.SSL_CLIENT_TRUSTSTORE_PASSWORD),
                Constants.HOSTNAME + ":9093", Constants.TOPIC_NAME);
    }

}
