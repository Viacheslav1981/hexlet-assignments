package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
class App {

    private static String data1 = " [program:options]\n" +
            "environment=\"X_FORWARDED_variable=value,  \"\n" +
            "\n" +
            "environment=\"key5=value5,X_FORWARDED_var3=value,key6=value6\"" +
            "\n" +
            "environment=\"X_FORWARDED_mail=tirion@google.com,X_FORWARDED_HOME=/home/tirion,language=en\"" +
            "\n" +
            "environment=\"key5=value5,X_FORWARDED_var3=value,key6=value6\"" +
            "\n" +
            "environment=\"pwd=/temp,user=tirion\"" +
            "\n" +
            "key=value" +
            "\n" +
            "command=X_FORWARDED_HOME=/ cd ~";


    public static void main(String[] args) {

        System.out.println(getForwardedVariables(data1));

    }

    public static String getForwardedVariables(String data) {

        String listOfEnvironment = Arrays.stream(data.split("\n"))
                .filter(x -> x.startsWith("environment") && x.contains("X_FORWARDED_"))
                .flatMap(x -> Arrays.stream(x.split("environment")))
                .flatMap(x -> Arrays.stream(x.split(",")))
                .filter(x -> x.contains("X_FORWARDED_"))
                .map(x -> x.replaceAll("=\"X_FORWARDED_", ""))
                .map(x -> x.replaceAll("X_FORWARDED_", ""))
                .map(x -> x.replaceAll("\"", ""))
                .collect(Collectors.joining(","));

        return listOfEnvironment;

    }
}

//END
