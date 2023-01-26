package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
class App {
    public static void main(String[] args) {
        
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
