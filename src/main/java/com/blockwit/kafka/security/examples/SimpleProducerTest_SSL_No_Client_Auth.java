package com.blockwit.kafka.security.examples;

public class SimpleProducerTest_SSL_No_Client_Auth {

    public static void main(String[] args) throws InterruptedException {
        SimpleProducer.runProducer(Helper.of("security.protocol", "SSL",
                "ssl.truststore.location", SSLConstants.SSL_CLIENT_TRUSTSTORE_PATH,
                "ssl.truststore.password", SSLConstants.SSL_CLIENT_TRUSTSTORE_PASSWORD),
                Constants.HOSTNAME + ":9093", Constants.TOPIC_NAME);
    }

}
