package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static void main(String[] args) {
        //String sentence = "the java is the best programming language java";
        String sentence = "";
        System.out.println(App.getWordCount(sentence));
        // System.out.println(App.getWordCount(""));

        Map wordsCount = App.getWordCount(sentence);
        String result = App.toString(wordsCount);

        System.out.println(result);


    }

    public static Map<String, Integer> getWordCount(String sentence) {

        String[] words = sentence.trim().split(" ");
        Map<String, Integer> wordsCount = new HashMap<>();
        if (sentence.equals("")) {
            wordsCount.clear();
            return wordsCount;
        }

        for (String word : words) {
            int countsWordInSentence = 0;
            for (String countWords : words) {
                if (word.equals(countWords)) {
                    countsWordInSentence++;
                }
            }
            wordsCount.put(word, countsWordInSentence);
        }
        return wordsCount;
    }

    public static String toString(Map<String, Integer> wordsCount) {

        String result = "{";

        for (Map.Entry<String, Integer> word : wordsCount.entrySet()) {
            result = result + '\n';
            result = result + "  " + word.getKey() + ": " + word.getValue();

        }
        if (wordsCount.size() == 0) {
            result = result + "}";
        } else {
            result = result + '\n' + "}";
        }
        return result;
    }
}

//END
