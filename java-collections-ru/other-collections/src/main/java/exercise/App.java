package exercise;

import java.util.*;

// BEGIN
class App {
    public static void main(String[] args) {

        Map<String, Object> data1 = new HashMap<>(
                Map.of("one", "eon", "two", "two", "four", true)
        );
        System.out.println(data1); //=> {two=two, four=true, one=eon}

        Map<String, Object> data2 = new HashMap<>(
                Map.of("two", "own", "zero", 4, "four", true)
        );
        System.out.println(data2); //=> {zero=4, two=own, four=true}

        Map<String, String> result = App.genDiff(data1, data2);
        System.out.println(result);
    }

    public static LinkedHashMap<String, String> genDiff(Map<String, Object> data1, Map<String, Object> data2) {

        LinkedHashMap<String, String> result = new LinkedHashMap<>();

        for (Map.Entry<String, Object> entry : data1.entrySet()) {
            result.put(entry.getKey(), null);
        }

        for (Map.Entry<String, Object> entry : data2.entrySet()) {
            result.put(entry.getKey(), null);
        }
        
        for (Map.Entry<String, String> resEntry : result.entrySet()) {
            String key1 = resEntry.getKey();

            if (data1.containsKey(key1)) {
                Object value1 = data1.get(key1);
                Object value2 = data2.get(key1);

                if (value2 == null) {
                    result.put(key1, "deleted");
                } else if (value2.equals(value1)) {
                    result.put(key1, "unchanged");
                } else {
                    result.put(key1, "changed");
                }
            } else {
                result.put(key1, "added");
            }
        }
        return result;
    }
}

//END
