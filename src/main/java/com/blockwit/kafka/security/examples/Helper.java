package com.blockwit.kafka.security.examples;

import java.util.HashMap;
import java.util.Map;

public class Helper {

    // instead of JDK 1.9 Map.of
    public static Map<String, String> of(String... args) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < args.length / 2; i++)
            map.put(args[i * 2], args[i * 2 + 1]);
        return map;
    }

}
