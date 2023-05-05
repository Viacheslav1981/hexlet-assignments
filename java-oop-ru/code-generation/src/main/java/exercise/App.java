package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static void main(String[] args) throws IOException {
        User user = new User(111, "Slava", "R", 42);

        System.out.println(user.getFirstName());

        Car car = new Car(111, "subaru", "forester", "white", user);
        String json = car.serialize();
        System.out.println(json);

        System.out.println(Car.unserialize(json).getBrand());

        Path path1 = Paths.get("src/test/resources/file1.json");
        Car car1 = new Car(1, "audi", "q3", "black", user);
        App.save(path1, car1); // Сохраняет представление объекта в файл

        Path path2 = Paths.get("src/test/resources/file1.json");
        Car car2 = App.extract(path2); // Возвращает инстанс класса Car
        System.out.println(car2.getModel());


    }

    public static void save(Path path, Car car) throws IOException {
        String json = car.serialize();

        try (FileWriter fileWriter = new FileWriter(path.toAbsolutePath().toString(), true)) {
            fileWriter.write(json);
        }
    }

    public static Car extract(Path path) throws IOException {

        String json = Files.readString(path);
        return Car.unserialize(json);
    }
}

// END
