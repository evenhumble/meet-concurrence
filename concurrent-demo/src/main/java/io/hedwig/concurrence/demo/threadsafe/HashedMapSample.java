package io.hedwig.concurrence.demo.threadsafe;

import java.util.HashMap;
import java.util.Map;

public class HashedMapSample {

    private Map<String, String> map = new HashMap();

    public void add(String key, String value) {
        map.put(key, value);
    }

    public String get(String key) {
        return map.getOrDefault(key, "");
    }
}
