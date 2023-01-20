package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
class App {
    public static void main(String[] args) {
      
    }

    public static Long getCountOfFreeEmails(List<String> emailsList) {

        Long count = emailsList.stream()
                .filter(email -> email.contains("@yandex.ru") ||
                        email.contains("@gmail.com") ||
                        email.contains("@hotmail.com"))
                .count();
        return count;
    }
}

// END
