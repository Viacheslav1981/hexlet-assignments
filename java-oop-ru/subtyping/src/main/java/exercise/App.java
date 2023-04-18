package exercise;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static void main(String[] args) throws IOException {

        Map<String, String> map = new HashMap<>();
        map.put("key", "10");
        map.put("key2", "20");


        KeyValueStorage storage = new InMemoryKV(map);

        Map<String, String> data = storage.toMap();
        System.out.println(data);

        System.out.println(storage.get("key", "def"));
        System.out.println(storage.get("key33", "def"));

        storage.set("key", "new");
        storage.unset("key33");

        swapKeyValue(storage);

        for (Map.Entry<String, String> elem : storage.toMap().entrySet()) {
            System.out.println(elem);
        }



        /*

      //  KeyValueStorage storage = new FileKV("src/test/resources/file", Map.of("key", "value"));
        // Получение значения по ключу
        System.out.println(storage.get("key", "default"));// "value"
        System.out.println(storage.get("key", "default"));// "value"
        storage.set("key2", "value3");
        storage.unset("key");

         */

    }


    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> newMap = new HashMap<>();

        for (Map.Entry<String, String> elem : storage.toMap().entrySet()) {
            String newKey = elem.getValue();
            String newValue = elem.getKey();
            newMap.put(newKey, newValue);
        }
        storage.toMap().clear();
        storage.toMap().putAll(newMap);
    }
}

// END
