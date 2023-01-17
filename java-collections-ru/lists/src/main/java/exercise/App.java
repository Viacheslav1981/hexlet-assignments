package exercise;

import java.util.Arrays;
import java.util.List;

// BEGIN
class App {
    public static void main(String[] args) {
        System.out.println(App.scrabble("rkqodlw", "woRld")); //true
        System.out.println(App.scrabble("begsdhhtsexoult", "Hexlet")); //true
        System.out.println(App.scrabble("thlxertwq", "hexlet")); //false
        System.out.println(App.scrabble("jvayu", "java")); //false
        System.out.println(App.scrabble("", "java")); //false

    }

    public static boolean scrabble(String letters, String word) {

        List<String> arrayOfLetters = Arrays.asList(letters.toLowerCase().split(""));
        List<String> arrayOfWord = Arrays.asList(word.toLowerCase().split(""));

        boolean isWord = false;

        for (int i = 0; i < arrayOfWord.size(); i++) {
            int countInWord = 0;
            int countInLetters = 0;

            for (int j = 0; j < arrayOfWord.size(); j++) {
                if (arrayOfWord.get(i).equals(arrayOfWord.get(j))) {
                    countInWord++;
                }
            }
            if (arrayOfLetters.contains(arrayOfWord.get(i))) {
                for (int j = 0; j < arrayOfLetters.size(); j++) {
                    if (arrayOfWord.get(i).equals(arrayOfLetters.get(j))) {
                        countInLetters++;
                    }

                }
            }

            if (countInLetters >= countInWord) {
                isWord = true;
            } else {
                isWord = false;
                break;
            }
        }

        return isWord;
    }
}


//END
