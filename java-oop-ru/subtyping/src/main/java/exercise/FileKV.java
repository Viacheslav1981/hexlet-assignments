package exercise;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    private String pathFile;
    private Map<String, String> map;
    private Map<String, String> mapHash = new HashMap<>();
    private String json;



    public FileKV(String pathFile, Map<String, String> map) {
        this.map = map;
        this.pathFile = pathFile;
        mapHash.putAll(map);
        json = Utils.serialize(mapHash);
        Utils.writeFile(pathFile, json);
    }

    @Override
    public void set(String key, String value) {
        mapHash.put(key, value);
        json = Utils.serialize(mapHash);
        Utils.writeFile(pathFile, json);
    }

    @Override
    public void unset(String key) throws IOException {
        String content = Utils.readFile(pathFile);
        Files.delete(Path.of(pathFile));
        Files.createFile(Path.of(pathFile));

        mapHash = Utils.unserialize(content);
        mapHash.remove(key);
        Utils.writeFile(pathFile, "");


        json = Utils.serialize(mapHash);
        Utils.writeFile(pathFile, json);
    }

    @Override
    public String get(String key, String defaultValue) {
        mapHash = Utils.unserialize(json);
        if (!(mapHash.get(key) == null)){
            return mapHash.get(key);
        }
        return defaultValue;
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(mapHash);
    }
}

// END
