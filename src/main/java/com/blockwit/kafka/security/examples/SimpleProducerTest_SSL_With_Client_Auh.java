package com.blockwit.kafka.security.examples;

public class SimpleProducerTest_SSL_With_Client_Auh {

    public static void main(String[] args) throws InterruptedException {
        SimpleProducer.runProducer(Helper.of("security.protocol", "SSL",
                "ssl.truststore.location", SSLConstants.SSL_CLIENT_TRUSTSTORE_PATH,
                "ssl.truststore.password", SSLConstants.SSL_CLIENT_TRUSTSTORE_PASSWORD,
                "ssl.keystore.location", SSLConstants.SSL_CLIENT_KEYSTORE_PATH,
                "ssl.keystore.password", SSLConstants.SSL_CLIENT_KEYSTORE_PASSWORD),
                Constants.HOSTNAME + ":9093", Constants.TOPIC_NAME);
    }

}
