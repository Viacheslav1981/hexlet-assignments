package exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        Home flat = new Flat(124, 5, 19);
        double area = flat.getArea();
        System.out.println(flat);

        Home cottage = new Cottage(135, 3);
        double areaCottage = cottage.getArea();
        System.out.println(cottage);

        System.out.println(flat.compareTo(cottage));


        List<Home> apartments = new ArrayList<>(List.of(
                new Flat(41, 3, 10),
                new Cottage(125.5, 2),
                new Flat(80, 10, 2),
                new Cottage(150, 3)
        ));

        List<String> result = App.buildApartmentsList(apartments, 3);
        System.out.println(result);

        CharSequence text = new ReversedSequence("abcdef");
        text = text.toString();
        char test = text.charAt(2);
        int length = text.length();
        String sub = text.subSequence(1, 4).toString();

        //   System.out.println(text);
        //   System.out.println(test);
        //   System.out.println(length);
        //   System.out.println(sub);


    }

    public static List<String> buildApartmentsList(List<Home> homeList, int count) {

        if (count > homeList.size()) {
            count = homeList.size();
        }


        List<String> retApart;
      //  List<Home> homes = new ArrayList<>();

        retApart = Collections.singletonList(homeList.stream()
                .sorted(Comparator.comparing(Home::getArea))

                .limit(count)
                .toList().toString());

     //   System.out.println(homes);

        /*
        Collections.sort(homeList);
        for (int i = 0; i < count; i++) {
            retApart.add(homeList.get(i).toString());


        }

         */
        return retApart;

    }
}

// BEGIN

// END
