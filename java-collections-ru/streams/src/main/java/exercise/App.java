package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
class App {
    public static void main(String[] args) {
        System.out.println("test");
        String[] emails = {
                "info@yandex.ru",
                "mk@host.com",
                "support@hexlet.io",
                "key@yandex.ru",
                "sergey@gmail.com",
                "vovan@gmail.com",
                "support.yandex.ru@host.com",
                "support.yandex.ru@hexlet.io"
        };
        List<String> emailsList = Arrays.asList(emails);
        System.out.println(getCountOfFreeEmails(emailsList));
    }

    public static Long getCountOfFreeEmails(List<String> emailsList) {

        Long count = emailsList.stream()
                .filter(email -> email.contains("@yandex.ru") || email.contains("@gmail.com") || email.contains("@hotmail.com"))
                .count();
        return count;
    }
}

// END
