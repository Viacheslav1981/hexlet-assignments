package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage{
    private Map<String, String> map;
    private Map<String, String> mapHash = new HashMap<>();

    public InMemoryKV(Map<String, String> map) {
        this.map = map;
        mapHash.putAll(map);

    }
    @Override
    public void set(String key, String value) {
        mapHash.put(key, value);
          //  map.put(key, value);
    }

    @Override
    public void unset(String key) {
        mapHash.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
       if (!(mapHash.get(key) == null)){
           return mapHash.get(key);
       }
        return defaultValue;
    }

    @Override
    public Map<String, String> toMap() {
      //  mapHash.putAll(map);
       // return map;
        // return mapHash ;
        return new HashMap<>(mapHash);

    }
}

// END
