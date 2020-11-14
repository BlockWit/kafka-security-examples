package com.blockwit.kafka.security.examples;

import java.util.Collections;

public class SimpleProducer_PLAINTEXT {

    public static void main(String[] args) throws InterruptedException {
        SimpleProducer.runProducer(Collections.emptyMap(),
                Constants.HOSTNAME + ":9092", Constants.TOPIC_NAME);
    }

}
