package exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

// BEGIN
class App {
    public static void main(String[] args) {
        List<Map<String, String>> books = new ArrayList<>();

        Map<String, String> book1 = new HashMap<>(
                Map.of("title", "Cymbeline", "author", "Shakespeare", "year", "1611")
        );
        Map<String, String> book2 = new HashMap<>(
                Map.of("title", "Book of Fooos", "author", "FooBar", "year", "1111")
        );
        Map<String, String> book3 = new HashMap<>(
                Map.of("title", "The Tempest", "author", "Shakespeare", "year", "1611")
        );
        Map<String, String> book4 = new HashMap<>(
                Map.of("title", "Book of Foos Barrrs", "author", "FooBar", "year", "2222")
        );
        Map<String, String> book5 = new HashMap<>(
                Map.of("title", "Still foooing", "author", "FooBar", "year", "3333")
        );

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

      //  Map<String, String> where = new HashMap<>(Map.of("author", "Shakespeare", "year", "1611"));
        /*
        Map<String, String> where = Map.of("title", "Still foooing",
                "author", "FooBar",
                "year", "3333"
        );

         */
        Map<String, String> where = Map.of("title", "Still foooing",
                "author", "FooBar",
                "year", "4444"
        );
        List<Map<String, String>> result = App.findWhere(books, where);

        System.out.println(result);


    }

    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> where) {
        List<Map<String, String>> mapReturn = new ArrayList<>();

        for (int i = 0; i < books.size(); i++) {
            Map<String, String> oneBook = books.get(i);
            boolean toAddBook = false;

            for (Map.Entry<String, String> book : where.entrySet()) {
                if (((oneBook.containsKey(book.getKey()) && oneBook.containsValue(book.getValue())))) {
                    toAddBook = true;
                } else {
                    toAddBook = false;
                    break;
                }
            }

            if (toAddBook) {
                mapReturn.add(oneBook);
            }
        }
        return mapReturn;
    }
}

//END
