package exercise;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {

        Map<String, String> attributes = new LinkedHashMap<>();
        attributes.put("lang", "ru");
        attributes.put("id", "abc");

        List<Tag> children = List.of(
                new SingleTag("br", Map.of("id", "s")),
                new SingleTag("hr", Map.of("class", "a-5"))
        );

        Tag div = new PairedTag("div", attributes, "", children);
        String actual = div.toString();

        System.out.println(actual);

        String expected = "<div lang=\"ru\" id=\"abc\"><br id=\"s\"><hr class=\"a-5\"></div>";




    }
}
